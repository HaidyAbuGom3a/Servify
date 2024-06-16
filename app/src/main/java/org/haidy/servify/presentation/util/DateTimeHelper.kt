package org.haidy.servify.presentation.util

import android.os.Build
import androidx.annotation.RequiresApi
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.utils.LanguageCode
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun convertToTimestamp(dateStr: String, timeStr: String): Long {
    val datetimeStr = "$dateStr ${timeStr.uppercase()}"
    val formatter =
        DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a").withLocale(Locale.ENGLISH)
    val datetime = LocalDateTime.parse(datetimeStr, formatter)
    val timestamp = datetime.atZone(ZoneId.systemDefault()).toEpochSecond()
    return timestamp
}

@RequiresApi(Build.VERSION_CODES.O)
fun Long.convertToDayOfMonth(): String{
    val locale =
        if (Resources.languageCode.value == LanguageCode.AR || Resources.languageCode.value == LanguageCode.EG) Locale(
            "ar"
        ) else Locale.getDefault()
    val formatter = SimpleDateFormat("dd MMM", locale)
    val date = Date(this * 1000)
    return formatter.format(date)
}

@RequiresApi(Build.VERSION_CODES.O)
fun getCurrentDateFormatted(): String {
    val currentDate = LocalDate.now()
    val locale =
        if (Resources.languageCode.value == LanguageCode.AR || Resources.languageCode.value == LanguageCode.EG) Locale(
            "ar"
        ) else Locale.getDefault()
    val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", locale)
    return currentDate.format(formatter)
}

@RequiresApi(Build.VERSION_CODES.O)
fun getCurrentTimeFormatted(): String {
    val currentTime = LocalTime.now()
    val locale =
        if (Resources.languageCode.value == LanguageCode.AR || Resources.languageCode.value == LanguageCode.EG) Locale(
            "ar"
        ) else Locale.getDefault()
    val formatter = DateTimeFormatter.ofPattern("hh:mm a", locale)

    return currentTime.format(formatter)
}