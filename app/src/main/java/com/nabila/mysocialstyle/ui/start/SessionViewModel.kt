package com.nabila.mysocialstyle.ui.start

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class SessionViewModel(private val authManager: AuthManager): ViewModel() {
    fun logout() = authManager.signOut()
    fun isLoggedIn(): Boolean = authManager.isLoggedIn()
}

interface AuthManager {
    fun signOut()
    fun isLoggedIn(): Boolean
}

class FirebaseAuthManager : AuthManager {
    override fun signOut() {
        FirebaseAuth.getInstance().signOut()
    }

    override fun isLoggedIn(): Boolean {
        return FirebaseAuth.getInstance().currentUser != null
    }
}
