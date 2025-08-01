package com.nabila.mysocialstyle.data

data class QuizResult(
    val uid: String = "",
    val name: String? = "",
    val socialStyleName: String = "",
    val amiableScore: Int = 0,
    val analyticalScore: Int = 0,
    val driverScore: Int = 0,
    val expressiveScore: Int = 0,
    val timeStamp: String = ""
)
