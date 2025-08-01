package com.nabila.mysocialstyle

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nabila.mysocialstyle.ui.result.ResultViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ResultUnitTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var result: ResultViewModel

    @Before
    fun setUp() {
        result = ResultViewModel()
    }

    @Test
    fun `if amiable score 10, then amiable percentage should 50`() {
        // Memanggil fungsi yang mengubah skor amiable menjadi persentase
        val amiablePercentage = result.amiablePercentage(10).toInt()
        // Membandingkan nilai ekspektasi dengan nilai sebenarnya
        assertEquals(50, amiablePercentage)
    }

    @Test
    fun `if analytical score 3, then analytical percentage should 15`() {
        // Memanggil fungsi yang mengubah skor analytical menjadi persentase
        val analyticalPercentage = result.analyticalPercentage(3).toInt()
        // Membandingkan nilai ekspektasi dengan nilai sebenarnya
        assertEquals(15, analyticalPercentage)
    }

    @Test
    fun `if expressive score 2, then expressive percentage should 10`() {
        // Memanggil fungsi yang mengubah skor expressive menjadi persentase
        val expressivePercentage = result.expressivePercentage(2).toInt()
        // Membandingkan nilai ekspektasi dengan nilai sebenarnya
        assertEquals(10, expressivePercentage)
    }

    @Test
    fun `if driver score 5, then driver percentage should 25`() {
        // Memanggil fungsi yang mengubah skor driver menjadi persentase
        val driverPercentage = result.driverPercentage(5).toInt()
        // Membandingkan nilai ekspektasi dengan nilai sebenarnya
        assertEquals(25, driverPercentage)
    }
}

