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
class ShareUnitTest {

    // Menjalankan tugas secara sinkron untuk LiveData
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    // ViewModel yang akan diuji
    private lateinit var share: ResultViewModel

    // Resource ID untuk gambar yang sesuai dengan gaya sosial
    val amiableImg = R.drawable.res_ami
    val analyticalImg = R.drawable.res_ana
    val expressiveImg = R.drawable.res_exp
    val driverImg = R.drawable.res_dri

    @Before
    fun setUp() {
        // Inisialisasi ViewModel sebelum setiap pengujian
        share = ResultViewModel()
    }

@Test
fun `if amiable score is highest, then shared img should be about amiable`() {
    // Memanggil fungsi shareQuiz yang mengembalikan gambar untuk dibagikan
    val img = share.shareQuiz(ami = 10, ana = 4, dri = 4, exp = 2)
    // Membandingkan nilai ekspektasi (amiableImg) dengan nilai sebenarnya (img)
    assertEquals(amiableImg, img)
}

@Test
fun `if analytical score is highest, then shared img should be about analytical`() {
    // Memanggil fungsi shareQuiz yang mengembalikan gambar untuk dibagikan
    val img = share.shareQuiz(ami = 4, ana = 10, dri = 4, exp = 2)
    // Membandingkan nilai ekspektasi (analyticalImg) dengan nilai sebenarnya (img)
    assertEquals(analyticalImg, img)
}

@Test
fun `if expressive score is highest, then shared img should be about expressive`() {
    // Memanggil fungsi shareQuiz yang mengembalikan gambar untuk dibagikan
    val img = share.shareQuiz(ami = 4, ana = 2, dri = 4, exp = 10)
    // Membandingkan nilai ekspektasi (expressiveImg) dengan nilai sebenarnya (img)
    assertEquals(expressiveImg, img)
}

@Test
fun `if driver score is highest, then shared img should be about driver`() {
    // Memanggil fungsi shareQuiz yang mengembalikan gambar untuk dibagikan
    val img = share.shareQuiz(ami = 2, ana = 4, dri = 10, exp = 4)
    // Membandingkan nilai ekspektasi (driverImg) dengan nilai sebenarnya (img)
    assertEquals(driverImg, img)
}
}
