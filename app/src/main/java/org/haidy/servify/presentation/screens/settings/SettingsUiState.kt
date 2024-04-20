package org.haidy.servify.presentation.screens.settings

import org.haidy.servify.app.theme.Theme
import org.haidy.servify.app.utils.ThemeMode

data class SettingsUiState(
    val isNotificationOn: Boolean = false,
    val showAlertDialog: Boolean = false,
    val themeMode: ThemeMode = Theme.themeMode.value
)
