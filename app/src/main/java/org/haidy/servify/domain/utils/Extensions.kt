package org.haidy.servify.domain.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import com.bumptech.glide.Glide
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.utils.LanguageCode
import java.io.File
import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

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
    val locale =
        if (Resources.languageCode.value == LanguageCode.AR || Resources.languageCode.value == LanguageCode.EG) Locale(
            "ar"
        ) else Locale.getDefault()

    val formatter = SimpleDateFormat("h aa - MMM dd, yyyy", locale)
    val date = Date(this * 1000)
    return formatter.format(date)
}

fun String.toTimeStamp(): Long{
    val formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy 'at' h:mm:ss a 'UTC'XXX", Locale.ENGLISH)
    val zonedDateTime = ZonedDateTime.parse(this, formatter)
    return zonedDateTime.toInstant().toEpochMilli()
}