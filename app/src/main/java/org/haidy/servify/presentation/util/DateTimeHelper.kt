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
import java.util.concurrent.TimeUnit

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


fun calculateTimeDifference(timeStamp: Long, language: LanguageCode): String {
    val currentTime = System.currentTimeMillis()
    val diffInMillis = currentTime - timeStamp

    val diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(diffInMillis)
    val diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillis)
    val diffInHours = TimeUnit.MILLISECONDS.toHours(diffInMillis)
    val diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillis)
    val diffInMonths = diffInDays / 30
    val diffInYears = diffInDays / 365

    return when (language) {
        LanguageCode.EN -> when {
            diffInYears > 0 -> "$diffInYears year${if (diffInYears > 1) "s" else ""}"
            diffInMonths > 0 -> "$diffInMonths month${if (diffInMonths > 1) "s" else ""}"
            diffInDays > 0 -> "$diffInDays day${if (diffInDays > 1) "s" else ""}"
            diffInHours > 0 -> "$diffInHours hour${if (diffInHours > 1) "s" else ""}"
            diffInMinutes > 0 -> "$diffInMinutes minute${if (diffInMinutes > 1) "s" else ""}"
            else -> "$diffInSeconds second${if (diffInSeconds > 1) "s" else ""}"
        }
        else -> when {
            diffInYears > 0 -> "$diffInYears ${if (diffInYears > 1) "سنوات" else "سنة"}"
            diffInMonths > 0 -> "$diffInMonths ${if (diffInMonths > 1) "أشهر" else "شهر"}"
            diffInDays > 0 -> "$diffInDays ${if (diffInDays > 1) "أيام" else "يوم"}"
            diffInHours > 0 -> "$diffInHours ${if (diffInHours > 1) "ساعات" else "ساعة"}"
            diffInMinutes > 0 -> "$diffInMinutes ${if (diffInMinutes > 1) "دقائق" else "دقيقة"}"
            else -> "$diffInSeconds ${if (diffInSeconds > 1) "ثواني" else "ثانية"}"
        }
    }
}