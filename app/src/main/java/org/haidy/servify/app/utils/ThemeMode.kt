package org.haidy.servify.app.utils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable

enum class ThemeMode(val code: String) {
    SYSTEM_THEME("system"),
    LIGHT_THEME("light"),
    DARK_THEME("dark");

    companion object {
        fun getThemeMode(code: String): ThemeMode {
            return values().find { it.code == code } ?: SYSTEM_THEME
        }
    }
}

@Composable
fun getIsDarkTheme(themeMode: ThemeMode): Boolean {
    return when (themeMode) {
        ThemeMode.SYSTEM_THEME -> isSystemInDarkTheme()
        ThemeMode.LIGHT_THEME -> false
        ThemeMode.DARK_THEME -> true
    }
}
