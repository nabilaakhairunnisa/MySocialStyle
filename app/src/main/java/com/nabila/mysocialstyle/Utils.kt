package com.nabila.mysocialstyle

import android.app.Activity
import android.content.Context
import android.graphics.PorterDuff
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.nabila.mysocialstyle.ui.quiz.btNext

val darkPurple = R.color.purple
val lightPurple = R.color.light_purple

fun Context.changeImageById(drawable: Int) {
    val activity = this as? Activity
    val imageView = activity?.findViewById<ImageView>(btNext)
    imageView?.setImageResource(drawable)
}

fun Context.changeColor(viewId: Int, color: Int) {
    val activity = this as? Activity
    val imageView = activity?.findViewById<ImageView>(viewId)
    imageView?.setColorFilter(
        ContextCompat.getColor(
            activity,
            color
        ), PorterDuff.Mode.SRC_IN
    )
}

fun showToast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

