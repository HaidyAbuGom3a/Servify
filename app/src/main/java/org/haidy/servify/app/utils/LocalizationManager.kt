package org.haidy.servify.app.utils

import androidx.compose.ui.unit.LayoutDirection
import org.haidy.servify.app.resources.strings.IStringResources
import org.haidy.servify.app.resources.strings.ar.Arabic
import org.haidy.servify.app.resources.strings.ar.ArabicEG
import org.haidy.servify.app.resources.strings.en.English

object LocalizationManager {
    fun getStringResources(languageCode: LanguageCode): IStringResources {
        return when (languageCode) {
            LanguageCode.EN -> English()
            LanguageCode.AR -> Arabic()
            LanguageCode.EG -> ArabicEG()
        }
    }

    fun getLayoutDirection(languageCode: LanguageCode): LayoutDirection {
        return when (languageCode) {
            LanguageCode.EN -> LayoutDirection.Ltr
            else -> LayoutDirection.Rtl
        }
    }
}