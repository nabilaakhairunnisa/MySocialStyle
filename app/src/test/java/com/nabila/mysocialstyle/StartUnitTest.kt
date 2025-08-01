package com.nabila.mysocialstyle

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nabila.mysocialstyle.ui.start.StartViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StartUnitTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var start: StartViewModel

    @Before
    fun setUp() {
        start = StartViewModel()
    }

    @Test
    fun `next button click should increase index`() {
        // Mengatur nilai indeks menjadi 0
        start.setIndex(0)
        // Memanggil fungsi next dari objek start
        start.next()
        // Membandingkan nilai ekspektasi dengan nilai sebenarnya
        assertEquals(1, start.index.value)
    }

    @Test
    fun `next button does not increase index beyond limit`() {
        // Mengatur nilai indeks menjadi 2
        start.setIndex(2)
        // Memanggil fungsi next dari objek start
        start.next()
        // Membandingkan nilai ekspektasi dengan nilai sebenarnya
        assertEquals(2, start.index.value)
    }

    @Test
    fun `prev button click should decrease index`() {
        // Mengatur nilai indeks menjadi 1
        start.setIndex(1)
        // Memanggil fungsi prev dari objek start
        start.prev()
        // Membandingkan nilai ekspektasi dengan nilai sebenarnya
        assertEquals(0, start.index.value)
    }

    @Test
    fun `prev button does not decrease index below limit`() {
        // Mengatur nilai indeks menjadi 1
        start.setIndex(0)
        // Memanggil fungsi prev dari objek start
        start.prev()
        // Membandingkan nilai ekspektasi dengan nilai sebenarnya
        assertEquals(0, start.index.value)
    }
}

