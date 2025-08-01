package com.nabila.mysocialstyle.ui.start

import android.content.Context
import com.nabila.mysocialstyle.*

//Start Utils

val prev = R.id.prev
val next = R.id.next

val darkPurple2 = R.color.dark_purple
val white = R.color.white

fun Context.enablePrev() = changeColor(prev, white)
fun Context.disablePrev() = changeColor(prev, darkPurple2)

fun Context.enableNext() = changeColor(next, white)
fun Context.disableNext() = changeColor(next, darkPurple2)