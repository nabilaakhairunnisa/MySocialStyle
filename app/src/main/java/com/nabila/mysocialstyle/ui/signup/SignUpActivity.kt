package com.nabila.mysocialstyle.ui.signup

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.nabila.mysocialstyle.R
import com.nabila.mysocialstyle.databinding.ActivitySignupBinding
import com.nabila.mysocialstyle.showToast
import com.nabila.mysocialstyle.ui.signin.SignInActivity
import com.nabila.mysocialstyle.ui.start.StartActivity
import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var viewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]

        lifecycleScope.launch {
            viewModel.formError.collect { error ->
                error?.let { (viewId, message) ->
                    when (viewId) {
                        R.id.inputName -> binding.inputName.error = message
                        R.id.inputEmail -> binding.inputEmail.error = message
                        R.id.inputPassword -> binding.inputPassword.error = message
                    }
                }
            }
        }

        binding.apply {
            buttonSignUp.setOnClickListener { signUp() }
            signIn.setOnClickListener { goToSignIn() }
        }
    }

    private fun signUp() {
        val name = binding.inputName.text.toString().trim()
        val email = binding.inputEmail.text.toString().trim()
        val password = binding.inputPassword.text.toString().trim()

        if (viewModel.validateForm(name, email, password)) {
            viewModel.signUpFirebase(name, email, password) { success, message ->
                if (success) {
                    showToast(this, message)
                    goToStartActivity()
                } else {
                    showToast(this, message)
                }
            }
        }
    }

    private fun goToSignIn() {
        startActivity(Intent(this@SignUpActivity, SignInActivity::class.java))
        finish()
    }

    private fun goToStartActivity() {
        startActivity(Intent(this@SignUpActivity, StartActivity::class.java))
        finish()
    }
}