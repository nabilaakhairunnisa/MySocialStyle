package com.nabila.mysocialstyle.ui.result

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nabila.mysocialstyle.data.QuizResult
import com.nabila.mysocialstyle.R
import java.util.Date
import java.util.Locale
import java.text.SimpleDateFormat

class ResultViewModel: ViewModel() {

    private val database = FirebaseDatabase.getInstance().reference
    private val auth = FirebaseAuth.getInstance()
    private var userName: String? = ""

    var ami = 0
    var ana = 0
    var dri = 0
    var exp = 0

    fun score(amiableScore: Int, analyticalScore: Int, driverScore: Int, expressiveScore: Int) {
        ami = amiableScore
        ana = analyticalScore
        dri = driverScore
        exp = expressiveScore
    }

    fun percentageAmi(): Float {
        return (ami.toFloat() / 20) * 100
    }

    fun percentageAna(): Float {
        return (ana.toFloat() / 20) * 100
    }

    fun percentageDri(): Float {
        return (dri.toFloat() / 20) * 100
    }

    fun percentageExp(): Float {
        return (exp.toFloat() / 20) * 100
    }

    fun saveResult() {
        val user = auth.currentUser
        if (user != null) {
            val uid = user.uid

            // Format tanggal
            val sdf = SimpleDateFormat("d MMMM yyyy HH:mm", Locale("id", "ID"))
            val timestamp = sdf.format(Date())

            // Data yang mau disimpan
            val socialStyleName = determineSocialStyle()

            //data user name yang mau disimpan
            database.child("User").child(uid)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        userName = snapshot.child("name").getValue(String::class.java)
                        val quizResult = QuizResult(
                            uid,
                            userName,
                            socialStyleName,
                            percentageAmi().toInt(),
                            percentageAna().toInt(),
                            percentageDri().toInt(),
                            percentageExp().toInt(),
                            timestamp
                        )
                        val resultRef = database.child("QuizResult")

                        // Cek apakah user sudah punya data
                        resultRef.orderByChild("uid").equalTo(uid)
                            .addListenerForSingleValueEvent(object : ValueEventListener {
                                override fun onDataChange(snapshot: DataSnapshot) {
                                    if (snapshot.exists()) {
                                        // Jika sudah ada → update data pertama yang ditemukan
                                        val existingKey = snapshot.children.first().key
                                        existingKey?.let {
                                            resultRef.child(it).setValue(quizResult)
                                        }
                                    } else {
                                        // Jika belum ada → buat baru dengan push
                                        val newKey = resultRef.push().key
                                        newKey?.let {
                                            resultRef.child(it).setValue(quizResult)
                                        }
                                    }
                                }
                                override fun onCancelled(error: DatabaseError) {
                                    Log.e("SaveResult", "Gagal cek hasil kuis: ${error.message}")
                                }
                            })
                    }
                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                }
            )
        }
    }

    private fun determineSocialStyle(): String {
        return when {
            ami >= ana && ami >= exp && ami >= dri -> "Amiable"
            ana >= ami && ana >= exp && ana >= dri -> "Analytical"
            exp >= ami && exp >= ana && exp >= dri -> "Expressive"
            else -> "Driver"
        }
    }

    //testing
    fun amiablePercentage(amiableScore: Int): Float {
        ami = amiableScore
        return (amiableScore.toFloat() / 20) * 100
    }

    fun analyticalPercentage(analyticalScore: Int): Float {
        ana = analyticalScore
        return (analyticalScore.toFloat() / 20) * 100
    }

    fun driverPercentage(driverScore: Int): Float {
        dri = driverScore
        return (driverScore.toFloat() / 20) * 100
    }

    fun expressivePercentage(expressiveScore: Int): Float {
        exp = expressiveScore
        return (expressiveScore.toFloat() / 20) * 100
    }

    fun shareQuiz(ami: Int, ana: Int, dri: Int, exp: Int): Int {
        var img = 0
        if (ami >= ana && ami >= dri && ami >= exp) img = R.drawable.res_ami
        if (ana >= ami && ana >= dri && ana >= exp) img = R.drawable.res_ana
        if (dri >= ami && dri >= ana && dri >= exp) img = R.drawable.res_dri
        if (exp >= ami && exp >= dri && exp >= ana) img = R.drawable.res_exp
        return img
    }

    fun retakeQuiz () {
        ami = 0
        ana = 0
        dri = 0
        exp = 0
    }

    fun clearAmiScore(): Int {
        ami = 0
        return ami
    }
    fun clearAnaScore(): Int {
        ana = 0
        return ana
    }
    fun clearExpScore(): Int {
        exp = 0
        return exp
    }
    fun clearDriScore(): Int {
        dri = 0
        return dri
    }
}