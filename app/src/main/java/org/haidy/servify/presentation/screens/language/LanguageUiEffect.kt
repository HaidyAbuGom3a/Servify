package org.haidy.servify.presentation.screens.language

import org.haidy.servify.app.utils.LanguageCode

sealed class LanguageUiEffect{
    object NavigateUp: LanguageUiEffect()
    data class UpdateLanguage(val language: LanguageCode): LanguageUiEffect()
}
