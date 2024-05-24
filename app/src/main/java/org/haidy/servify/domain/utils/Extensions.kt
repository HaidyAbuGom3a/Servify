package org.haidy.servify.domain.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import com.bumptech.glide.Glide
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

fun Context.getUriAsFile(uri: Uri): File? {
    return try {
        Glide.with(this)
            .asFile()
            .load(uri)
            .submit()
            .get()
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

@SuppressLint("SimpleDateFormat")
fun Long.toDateString(): String {
    val formatter = SimpleDateFormat("h aa - MMM dd, yyyy")
    val date = Date(this * 1000)
    return formatter.format(date)
}