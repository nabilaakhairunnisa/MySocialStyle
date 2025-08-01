package com.nabila.mysocialstyle.ui.start

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.nabila.mysocialstyle.ui.quiz.QuizActivity
import com.nabila.mysocialstyle.databinding.ActivityStartBinding
import com.nabila.mysocialstyle.*
import com.nabila.mysocialstyle.ui.history.HistoryActivity
import com.nabila.mysocialstyle.ui.signin.SignInActivity

class StartActivity : AppCompatActivity() {

    private val startViewModel: StartViewModel by viewModels()
    private lateinit var sessionViewModel: SessionViewModel
    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = SessionViewModelFactory(FirebaseAuthManager())
        sessionViewModel = ViewModelProvider(this, factory)[SessionViewModel::class.java]

        if (!sessionViewModel.isLoggedIn()) {
            goToSignIn()
        }

        startViewModel.index.observe(this) { index->
            isFirstInformation(index)
            isLastInformation(index)
            showInformation(index)
        }

        binding.apply {
            next.setOnClickListener { startViewModel.next() }
            prev.setOnClickListener { startViewModel.prev() }
            btStart.setOnClickListener { goToQuizPage() }
            btLogout.setOnClickListener { showLogoutDialog() }
            bthistory.setOnClickListener { goToHistory() }
        }
    }

    private fun showLogoutDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Konfirmasi Logout")
        builder.setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")
        builder.setPositiveButton("Ya") { _, _ ->
            sessionViewModel.logout()
            val intent = Intent(this, SignInActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
        builder.setNegativeButton("Tidak") { dialog, _ ->
            dialog.dismiss()
        }
        builder.create().show()
    }


    private fun showInformation(i: Int) {
        val titles = resources.getStringArray(R.array.app_title)
        val descriptions = resources.getStringArray(R.array.app_description)

        binding.apply {
            title.text = titles[i]
            desc.text = descriptions[i]
        }
    }

    private fun isFirstInformation(i: Int) {
        if (i == 0) disablePrev()
        else enablePrev()
    }

    private fun isLastInformation(i: Int) {
        if (i == 2) disableNext()
        else enableNext()
    }

    private fun goToSignIn() {
        startActivity(Intent(this@StartActivity, SignInActivity::class.java))
        finish()
    }

    private fun goToHistory() {
        startActivity(Intent(this@StartActivity, HistoryActivity::class.java))
    }

    private fun goToQuizPage() {
        startActivity(Intent(this@StartActivity, QuizActivity::class.java))
        finish()
    }
}