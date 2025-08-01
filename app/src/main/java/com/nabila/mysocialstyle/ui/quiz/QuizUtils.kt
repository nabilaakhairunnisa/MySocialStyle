package com.nabila.mysocialstyle.ui.quiz

import android.app.Activity
import android.content.Context
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.nabila.mysocialstyle.*

//Quiz Activity

val btPrev = R.id.btPrev
val btNext = R.id.btNext

val enableNext = R.drawable.ic_next
val disableNext = R.drawable.ic_next_disable
val enableFinish = R.drawable.bt_finish
val disableFinish = R.drawable.bt_unfinish

val amiableId = R.id.amiableAnswer
val analyticalId = R.id.analyticalAnswer
val expressiveId = R.id.expressiveAnswer
val driverId = R.id.driverAnswer

fun showErrorMessage(context: Context) {
    Toast.makeText(
        context,
        R.string.error_msg,
        Toast.LENGTH_SHORT
    ).show()
}

fun Context.answerId(): Int {
    val activity = this as Activity
    val radio = activity.findViewById<RadioGroup>(R.id.radioGroup)
    return radio.checkedRadioButtonId
}

fun Context.showqna(index:Int) {
    val activity = this as Activity
    val question = activity.findViewById<TextView>(R.id.tvQuestion)
    val amiableAnswer = activity.findViewById<RadioButton>(amiableId)
    val analyticalAnswer = activity.findViewById<RadioButton>(analyticalId)
    val expressiveAnswer = activity.findViewById<RadioButton>(expressiveId)
    val driverAnswer = activity.findViewById<RadioButton>(driverId)
    val count = activity.findViewById<TextView>(R.id.count)

    question.text = resources.getStringArray(R.array.questions)[index]
    amiableAnswer.text = resources.getStringArray(R.array.amiableAnswers)[index]
    analyticalAnswer.text = resources.getStringArray(R.array.analyticalAnswers)[index]
    expressiveAnswer.text = resources.getStringArray(R.array.expressiveAnswers)[index]
    driverAnswer.text = resources.getStringArray(R.array.driverAnswers)[index]
    count.text = (index + 1).toString()
}

fun Context.enablePrevButton() = changeColor(btPrev, darkPurple)
fun Context.disablePrevButton() = changeColor(btPrev, lightPurple)

fun Context.enableNextButton() = changeImageById(enableNext)
fun Context.disableNextButton() = changeImageById(disableNext)

fun Context.enableFinishButton() = changeImageById(enableFinish)
fun Context.disableFinishButton() = changeImageById(disableFinish)