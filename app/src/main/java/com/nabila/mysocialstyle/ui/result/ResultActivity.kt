package com.nabila.mysocialstyle.ui.result

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.nabila.mysocialstyle.databinding.ActivityResultBinding
import com.nabila.mysocialstyle.ui.quiz.QuizActivity
import com.nabila.mysocialstyle.ui.start.StartActivity

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    private val viewModel: ResultViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ami = intent.getIntExtra("AmiableResult", 0)
        val ana = intent.getIntExtra("AnalyticalResult", 0)
        val exp = intent.getIntExtra("ExpressiveResult", 0)
        val dri = intent.getIntExtra("DriverResult", 0)

        viewModel.score(ami, ana, dri, exp)
        val scoreAmi = viewModel.percentageAmi().toInt()
        val scoreAna = viewModel.percentageAna().toInt()
        val scoreExp = viewModel.percentageExp().toInt()
        val scoreDri = viewModel.percentageDri().toInt()
        val imgRes = viewModel.shareQuiz(ami, ana, dri, exp)
        viewModel.saveResult()

        if (ami >= ana && ami >= exp && ami >= dri) { showAmiable() }
        if (ana >= ami && ana >= exp && ana >= dri) { showAnalytical() }
        if (exp >= ami && exp >= ana && exp >= dri) { showExpressive() }
        if (dri >= ami && dri >= ana && dri >= exp) { showDriver() }

        binding.apply {
            amiableScore.text = scoreAmi.toString()
            analyticalScore.text = scoreAna.toString()
            expressiveScore.text = scoreExp.toString()
            driverScore.text = scoreDri.toString()

            tabAmiable.setOnClickListener { showAmiable() }
            tabAnalytical.setOnClickListener { showAnalytical() }
            tabExpressive.setOnClickListener { showExpressive() }
            tabDriver.setOnClickListener { showDriver() }

            share.setOnClickListener { setImage(imgRes) }
            startAgain.setOnClickListener { goToQuizPage(); finish() }

        }
    }

    private fun setImage(imageRes: Int) {
        val image = ContextCompat.getDrawable(this@ResultActivity, imageRes)
        if (image is BitmapDrawable) {
            val mBitmap = image.bitmap
            val path = MediaStore.Images.Media.insertImage(
                contentResolver,
                mBitmap,
                "MySocialStyle",
                null
            )
            val uri = Uri.parse(path)
            goToSelectedApp(uri)
        }
    }

    private fun goToSelectedApp(uri: Uri){
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "image/*"
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
        startActivity(Intent.createChooser(shareIntent, "Share Result"))
    }

    private fun goToQuizPage() {
        viewModel.retakeQuiz()
        startActivity(Intent(this@ResultActivity, QuizActivity::class.java))
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, StartActivity::class.java)
        startActivity(intent)
        finish()
    }

}