package org.haidy.servify.presentation.screens.settings

import org.haidy.servify.app.utils.ThemeMode

sealed class SettingsUiEffect{
    object NavigateToEditProfile: SettingsUiEffect()
    object NavigateToLanguage: SettingsUiEffect()
    object NavigateToNotifications: SettingsUiEffect()
    object NavigateToUpdatePassword: SettingsUiEffect()
    object NavigateToHelpAndSupport: SettingsUiEffect()
    object NavigateToContactUs: SettingsUiEffect()
    object NavigateToPrivacyPolicy: SettingsUiEffect()
    object NavigateUp: SettingsUiEffect()
    object NavigateToLogin: SettingsUiEffect()
    data class UpdateTheme(val themeMode: ThemeMode): SettingsUiEffect()
}