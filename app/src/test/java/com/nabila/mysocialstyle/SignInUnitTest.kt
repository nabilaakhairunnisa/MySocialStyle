package com.nabila.mysocialstyle

import com.nabila.mysocialstyle.ui.signin.SignInViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SignInUnitTest {

    private lateinit var viewModel: SignInViewModel

    @Before
    fun setUp() {
        viewModel = SignInViewModel()
    }

    @Test
    fun `login valid with correct input`() {
        val result = viewModel.validateForm("nabila@mail.com", "123456")
        assertTrue(result)
    }

    @Test
    fun `login invalid with incorrect email`() {
        val result = viewModel.validateForm("nabila_at_mail.com", "123456")
        assertFalse(result)
    }

    @Test
    fun `login invalid with short password`() {
        val result = viewModel.validateForm("nabila@mail.com", "123")
        assertFalse(result)
    }
}

