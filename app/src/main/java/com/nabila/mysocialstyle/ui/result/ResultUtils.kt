package com.nabila.mysocialstyle.ui.result

import android.app.Activity
import android.content.Context
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.BulletSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.nabila.mysocialstyle.*
import com.nabila.mysocialstyle.ui.start.white

//Result Utils

val tabAmiable = R.id.tab_amiable
val tabAnalytical = R.id.tab_analytical
val tabExpressive = R.id.tab_expressive
val tabDriver = R.id.tab_driver

fun Context.removeColor() {
    cardViewColor(tabAmiable, white)
    cardViewColor(tabAnalytical, white)
    cardViewColor(tabExpressive, white)
    cardViewColor(tabDriver, white)
}

fun Context.cardViewColor(cardViewId: Int, color: Int) {
    val activity = this as Activity
    val cardView = activity.findViewById<CardView>(cardViewId)
    cardView.setBackgroundColor(ContextCompat.getColor(activity, color))
}

fun TextView.setBulletedText(context: Context, stringResId: Int) {
    val textFromResources = context.getString(stringResId)
    //setiap menenukan \n maka simpan beberapa teks ke variabel lines
    val lines = textFromResources.split("\n")
    val spannableStringBuilder = SpannableStringBuilder()

    val indentPx = context.resources.getDimensionPixelSize(R.dimen.bullet_indent_gap)

    for (line in lines) {
        if (line.trim().isNotBlank()) {
            val trimmedLine = line.trimStart(' ')
            val spannableLine = SpannableString(trimmedLine)
            spannableLine.setSpan(
                BulletSpan(
                    indentPx,
                    ContextCompat.getColor(context, R.color.black)
                ), 0, spannableLine.length, Spanned.SPAN_POINT_MARK)
            spannableStringBuilder.append(spannableLine)
            spannableStringBuilder.append("\n")
        }
    }
    this.text = spannableStringBuilder.trimEnd()
}


fun Context.showResult(
    resImage: Int,
    resTitle: Int,
    resChar: Int,
    resHowToCommunicate: Int,
    resStrength: Int,
    resWeakness: Int,
    resSolution: Int,
    resRole: Int
) {
    val activity = this as Activity

    val image = activity.findViewById<ImageView>(R.id.socialStyleImg)
    val title = activity.findViewById<TextView>(R.id.titleResult)
    val characteristics = activity.findViewById<TextView>(R.id.characteristics)
    val howToCommunicate = activity.findViewById<TextView>(R.id.howToComunicate)
    val strength = activity.findViewById<TextView>(R.id.strength)
    val weakness = activity.findViewById<TextView>(R.id.weakness)
    val solution = activity.findViewById<TextView>(R.id.solutions)
    val role = activity.findViewById<TextView>(R.id.role)

    image.setImageResource(resImage)
    title.text = activity.getString(resTitle)
    characteristics.setBulletedText(activity, resChar)
    howToCommunicate.setBulletedText(activity, resHowToCommunicate)
    strength.setBulletedText(activity, resStrength)
    weakness.setBulletedText(activity, resWeakness)
    solution.setBulletedText(activity, resSolution)
    role.setBulletedText(activity, resRole)
}

fun Context.showAmiable() {
    removeColor()
    cardViewColor(R.id.tab_amiable, R.color.amiableColor, )
    showResult(
        R.drawable.img_ami,
        R.string.amiable,
        R.string.desc_ami,
        R.string.how_to_communicate_ami,
        R.string.strengths_ami,
        R.string.weakness_ami,
        R.string.solution_ami,
        R.string.role_ami
    )
}

fun Context.showAnalytical() {
    removeColor()
    cardViewColor(R.id.tab_analytical, R.color.analyticalColor)
    showResult(
        R.drawable.img_ana,
        R.string.analytical,
        R.string.desc_ana,
        R.string.how_to_communicate_ana,
        R.string.strengths_ana,
        R.string.weakness_ana,
        R.string.solution_ana,
        R.string.role_ana
    )
}

fun Context.showExpressive() {
    removeColor()
    cardViewColor(R.id.tab_expressive, R.color.expressiveColor, )
    showResult(
        R.drawable.img_exp,
        R.string.expressive,
        R.string.desc_exp,
        R.string.how_to_communicate_exp,
        R.string.strengths_exp,
        R.string.weakness_exp,
        R.string.solution_exp,
        R.string.role_exp
    )
}

fun Context.showDriver() {
    removeColor()
    cardViewColor(R.id.tab_driver, R.color.driverColor, )
    showResult(
        R.drawable.img_dri,
        R.string.driver,
        R.string.desc_dri,
        R.string.how_to_communicate_dri,
        R.string.strengths_dri,
        R.string.weakness_dri,
        R.string.solution_dri,
        R.string.role_dri
    )
}

