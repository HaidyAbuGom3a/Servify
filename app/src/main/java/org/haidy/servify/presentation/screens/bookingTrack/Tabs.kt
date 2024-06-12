package org.haidy.servify.presentation.screens.bookingTrack

import org.haidy.servify.app.utils.LanguageCode
import org.haidy.servify.app.utils.LocalizationManager

enum class Tabs {
    UPCOMING,
    COMPLETED,
    CANCELED;
    fun getTitle(languageCode: LanguageCode): String {
        val stringRes = LocalizationManager.getStringResources(languageCode)
        return when (this) {
            UPCOMING -> stringRes.upcoming
            COMPLETED -> stringRes.completed
            CANCELED -> stringRes.canceled
        }
    }
}