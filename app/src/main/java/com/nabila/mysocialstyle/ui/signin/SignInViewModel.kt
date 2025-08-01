package com.nabila.mysocialstyle.ui.signin

import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.auth
import com.nabila.mysocialstyle.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignInViewModel : ViewModel() {

    private val _formError = MutableStateFlow<Pair<Int, String>?>(null)
    val formError: StateFlow<Pair<Int, String>?> = _formError

    fun validateForm(email: String, password: String): Boolean {

        _formError.value = null
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")

        return when {
            email.isEmpty() -> {
                _formError.value = R.id.inputEmail to "Email tidak boleh kosong!"
                false
            }
            !email.matches(emailRegex) -> {
                _formError.value = R.id.inputEmail to "Email tidak valid!"
                false
            }
            password.isEmpty() -> {
                _formError.value = R.id.inputPassword to "Password tidak boleh kosong!"
                false
            }
            password.length < 6 -> {
                _formError.value = R.id.inputPassword to "Password minimal 6 karakter!"
                false
            }
            else -> true
        }
    }

    fun signInFirebase(
        email: String,
        password: String,
        callback: (Boolean, String) -> Unit
    ) {
        val auth = Firebase.auth
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, "Login berhasil!")
                } else {
                    when (val exception = task.exception) {
                        is FirebaseAuthInvalidUserException -> {
                            callback(false, "Akun tidak ditemukan")
                        }
                        is FirebaseAuthInvalidCredentialsException -> {
                            callback(false, "Password atau Email salah")
                        }
                        is FirebaseTooManyRequestsException -> {
                            callback(false, "Terlalu banyak permintaan, coba lagi nanti")
                        }
                        is FirebaseNetworkException -> {
                            callback(false, "Gagal terhubung ke jaringan")
                        }
                        else -> {
                            val errorMessage = exception?.localizedMessage ?: "Login gagal"
                            callback(false, errorMessage)
                        }
                    }
                }
            }
    }
}
