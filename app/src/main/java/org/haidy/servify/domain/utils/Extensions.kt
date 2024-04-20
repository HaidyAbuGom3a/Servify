package org.haidy.servify.domain.utils

import android.content.Context
import android.net.Uri
import com.bumptech.glide.Glide
import java.io.File

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