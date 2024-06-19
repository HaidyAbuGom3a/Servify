package org.haidy.servify.data.util

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun getCurrentTimeFormatted(): String {
    val currentDateTime = ZonedDateTime.now().withZoneSameInstant(java.time.ZoneId.of("UTC+3"))
    val formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy 'at' h:mm:ss a 'UTC'XXX", Locale.ENGLISH)
    return currentDateTime.format(formatter)
}