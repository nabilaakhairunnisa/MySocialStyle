package com.nabila.mysocialstyle

import com.nabila.mysocialstyle.ui.start.AuthManager
import com.nabila.mysocialstyle.ui.start.SessionViewModel
import junit.framework.TestCase.assertFalse
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class LogoutTest {

    private val authManager = mock(AuthManager::class.java)
    private val viewModel = SessionViewModel(authManager)

    @Test
    fun logoutShouldCallAuthManagerSignOut() {
        viewModel.logout()
        verify(authManager).signOut()
    }

    @Test
    fun isLoggedInReturnsFalseWhenAuthManagerReturnsFalse() {
        `when`(authManager.isLoggedIn()).thenReturn(false)
        assertFalse(viewModel.isLoggedIn())
        verify(authManager).isLoggedIn()
    }
}