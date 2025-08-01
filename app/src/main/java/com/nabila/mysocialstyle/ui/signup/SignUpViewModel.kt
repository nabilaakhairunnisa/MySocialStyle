package com.nabila.mysocialstyle.ui.signup

import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase
import com.nabila.mysocialstyle.R
import com.nabila.mysocialstyle.data.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignUpViewModel : ViewModel() {

    private val _formError = MutableStateFlow<Pair<Int, String>?>(null)
    val formError: StateFlow<Pair<Int, String>?> = _formError

    fun validateForm(name: String, email: String, password: String): Boolean {

        _formError.value = null
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")

        return when {
            name.isEmpty() -> {
                _formError.value = R.id.inputName to "Nama tidak boleh kosong!"
                false
            }
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

    fun signUpFirebase(
        name: String,
        email: String,
        password: String,
        callback: (Boolean, String) -> Unit
    ) {
        val auth = Firebase.auth
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val profileUpdates = userProfileChangeRequest {
                        displayName = name
                    }

                    user?.updateProfile(profileUpdates)?.addOnCompleteListener {
                        saveUserToDatabase(user.uid, name, email)
                        callback(true, "Registrasi berhasil!")
                    }
                } else {
                    when (val exception = task.exception) {
                        is FirebaseAuthUserCollisionException -> {
                            callback(false, "Email sudah terdaftar")
                        }
                        is FirebaseTooManyRequestsException -> {
                            callback(false, "Terlalu banyak permintaan, coba lagi nanti")
                        }
                        is FirebaseNetworkException  -> {
                            callback(false, "Gagal terhubung ke jaringan")
                        }
                        else -> {
                            val errorMessage = exception?.localizedMessage ?: "Registrasi gagal"
                            callback(false, errorMessage)
                        }
                    }
                }
            }
    }

    private fun saveUserToDatabase(uid: String, name: String, email: String) {
        val database = FirebaseDatabase.getInstance().reference
        val user = User(uid, name, email)
        database.child("User").child(uid).setValue(user)
    }
}
