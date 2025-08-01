package com.nabila.mysocialstyle.ui.signin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.nabila.mysocialstyle.R
import com.nabila.mysocialstyle.databinding.ActivitySigninBinding
import com.nabila.mysocialstyle.showToast
import com.nabila.mysocialstyle.ui.signup.SignUpActivity
import com.nabila.mysocialstyle.ui.start.StartActivity
import kotlinx.coroutines.launch

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySigninBinding
    private lateinit var viewModel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[SignInViewModel::class.java]

        lifecycleScope.launch {
            viewModel.formError.collect { error ->
                error?.let { (viewId, message) ->
                    when (viewId) {
                        R.id.inputEmail -> binding.inputEmail.error = message
                        R.id.inputPassword -> binding.inputPassword.error = message
                    }
                }
            }
        }

        binding.apply {
            signUp.setOnClickListener { goToSignUp() }
            buttonSignIn.setOnClickListener { signIn() }
        }
    }

    private fun signIn() {
        val email = binding.inputEmail.text.toString().trim()
        val password = binding.inputPassword.text.toString().trim()

        if (viewModel.validateForm(email, password)) {
            viewModel.signInFirebase(email, password) { success, message ->
                if (success) {
                    showToast(this, message)
                    goToStartActivity()
                } else {
                    showToast(this, message)
                }
            }
        }
    }

    private fun goToSignUp() {
        startActivity(Intent(this@SignInActivity, SignUpActivity::class.java))
        finish()
    }

    private fun goToStartActivity() {
        startActivity(Intent(this@SignInActivity, StartActivity::class.java))
        finish()
    }
}