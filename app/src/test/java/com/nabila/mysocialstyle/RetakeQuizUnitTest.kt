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
class RetakeQuizUnitTest {

    // Menjalankan tugas secara sinkron untuk LiveData
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    // ViewModel yang akan diuji
    private lateinit var retake: ResultViewModel

    @Before
    fun setUp() {
        // Inisialisasi ViewModel sebelum setiap pengujian
        retake = ResultViewModel()

    }

@Test
fun `if retake quiz, score should be empty`() {
    //mengakses nilai aktual skor masing-masing gaya sosial
    var amiScore = retake.ami
    var anaScore = retake.ana
    var driScore = retake.dri
    var expScore = retake.exp
    //memberikan nilai ke masing-masing skor gaya sosial
    amiScore = 10
    anaScore = 2
    driScore = 5
    expScore = 3
    //Memanggil fungsi retakeQuiz
    val newAmiScore = retake.clearAmiScore()
    val newAnaScore = retake.clearAnaScore()
    val newExpScore = retake.clearExpScore()
    val newDriScore = retake.clearDriScore()
    //Membandingkan nilai ekspektasi (0) dengan nilai sebenarnya
    assertEquals(0, newAmiScore)
    assertEquals(0, newAnaScore)
    assertEquals(0, newDriScore)
    assertEquals(0, newExpScore)
}

@Test
fun `clear amiable score if retake quiz`() {
    var amiScore = retake.ami //mengakses variabel yang menyimpan skor amiable
    amiScore = 10 //memberikan nilai awal skor amiable
    val newAmiScore = retake.clearAmiScore() //Memanggil menghapus skor amiable
    assertEquals(0, newAmiScore) //memeriksa apakah skor sudah bernilai 0
}
}
