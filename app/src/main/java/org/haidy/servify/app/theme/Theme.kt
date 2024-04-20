package org.haidy.servify.app.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import org.haidy.servify.app.resources.DrawableDarkResources
import org.haidy.servify.app.resources.DrawableResources
import org.haidy.servify.app.resources.localDrawableResources
import org.haidy.servify.app.resources.localStringResources
import org.haidy.servify.app.utils.LanguageCode
import org.haidy.servify.app.utils.LocalizationManager
import org.haidy.servify.app.utils.ThemeMode
import org.haidy.servify.app.utils.getAnimatedColorScheme
import org.haidy.servify.app.utils.getIsDarkTheme


private val localColorScheme = staticCompositionLocalOf { LightColors }
private val localRadius = staticCompositionLocalOf { Radius() }
private val localTypography = staticCompositionLocalOf { Typography() }
val localThemeMode = staticCompositionLocalOf { ThemeMode.SYSTEM_THEME }


@Composable
fun ServifyTheme(
    themeMode: ThemeMode = ThemeMode.SYSTEM_THEME,
    languageCode: LanguageCode,
    content: @Composable () -> Unit,
) {
    val useDarkTheme: Boolean = getIsDarkTheme(themeMode = themeMode)
    val colorScheme: Colors = if (useDarkTheme) DarkColors else LightColors
    val drawableResources = if (useDarkTheme) DrawableDarkResources else DrawableResources()

    val typography = Typography(
        headlineLarge = headlineLarge(),
        headline = headline(),
        titleLarge = titleLarge(),
        title = title(),
        bodyLarge = bodyLarge(),
        body = body(),
        caption = caption(),
    )

    CompositionLocalProvider(
        localColorScheme provides getAnimatedColorScheme(colorScheme),
        localTypography provides typography,
        localRadius provides Radius(),
        localDrawableResources provides drawableResources,
        localStringResources provides LocalizationManager.getStringResources(languageCode),
        LocalLayoutDirection provides LocalizationManager.getLayoutDirection(languageCode),
        localThemeMode provides themeMode
    ) {
        content()
    }
}

object Theme {
    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = localColorScheme.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = localTypography.current

    val radius: Radius
        @Composable
        @ReadOnlyComposable
        get() = localRadius.current

    val themeMode = mutableStateOf(ThemeMode.SYSTEM_THEME)

    fun switchTheme(mode: ThemeMode) {
        themeMode.value = mode
    }
}

