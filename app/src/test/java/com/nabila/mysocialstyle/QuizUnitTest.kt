package com.nabila.mysocialstyle

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nabila.mysocialstyle.ui.quiz.QuizViewModel
import com.nabila.mysocialstyle.ui.quiz.amiableId
import com.nabila.mysocialstyle.ui.quiz.analyticalId
import com.nabila.mysocialstyle.ui.quiz.driverId
import com.nabila.mysocialstyle.ui.quiz.expressiveId
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class QuizUnitTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var quiz: QuizViewModel

    @Before
    fun setUp() {
        quiz = QuizViewModel()
    }

    @Test
    fun `if amiable answer chosen should increase amiable score`() {
        // Menyimpan jawaban amiable pada pertanyaan nomor 2
        quiz.saveAnswer(amiableId, 2)
        // Membandingkan nilai ekspektasi dengan nilai sebenarnya
        assertEquals(1, quiz.amiableCount)
    }

    @Test
    fun `if analytical answer chosen should increase analytical score`() {
        // Menyimpan jawaban analytical pada pertanyaan nomor 10
        quiz.saveAnswer(analyticalId, 10)
        // Membandingkan nilai ekspektasi dengan nilai sebenarnya
        assertEquals(1, quiz.analyticalCount)
    }

    @Test
    fun `if driver answer chosen should increase driver score`() {
        // Menyimpan jawaban driver pada pertanyaan nomor 15
        quiz.saveAnswer(driverId, 15)
        // Membandingkan nilai ekspektasi dengan nilai sebenarnya
        assertEquals(1, quiz.driverCount)
    }

    @Test
    fun `if expressive answer chosen should increase expressive score`() {
        // Menyimpan jawaban expressive pada pertanyaan nomor 20
        quiz.saveAnswer(expressiveId, 20)
        // Membandingkan nilai ekspektasi dengan nilai sebenarnya
        assertEquals(1, quiz.expressiveCount)
    }
}

