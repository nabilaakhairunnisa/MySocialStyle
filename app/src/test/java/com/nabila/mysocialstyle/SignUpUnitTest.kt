package com.nabila.mysocialstyle

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nabila.mysocialstyle.ui.signup.SignUpViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpUnitTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: SignUpViewModel

    @Before
    fun setUp() {
        viewModel = SignUpViewModel()
    }

    @Test
    fun `form valid with correct input`() {
        val result = viewModel.validateForm("Nabila", "nabila@mail.com", "123456")
        assertTrue(result)
    }

    @Test
    fun `form invalid when name is empty`() {
        val result = viewModel.validateForm("", "nabila@mail.com", "123456")
        assertFalse(result)
    }

    @Test
    fun `form invalid when email is invalid`() {
        val result = viewModel.validateForm("Nabila", "not-an-email", "123456")
        assertFalse(result)
    }

    @Test
    fun `form invalid when password is less than 6 characters`() {
        val result = viewModel.validateForm("Nabila", "nabila@mail.com", "123")
        assertFalse(result)
    }
}
