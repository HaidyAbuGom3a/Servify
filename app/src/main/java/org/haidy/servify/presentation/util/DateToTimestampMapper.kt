package org.haidy.servify.presentation.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun convertToTimestamp(dateStr: String, timeStr: String): Long {
    val datetimeStr = "$dateStr ${timeStr.uppercase()}"
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a").withLocale(java.util.Locale.ENGLISH)
    val datetime = LocalDateTime.parse(datetimeStr, formatter)
    val timestamp = datetime.atZone(ZoneId.systemDefault()).toEpochSecond()
    return timestamp
}