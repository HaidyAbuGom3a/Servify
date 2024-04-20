package org.haidy.servify.app.resources

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.staticCompositionLocalOf
import org.haidy.servify.app.resources.strings.IStringResources
import org.haidy.servify.app.utils.LanguageCode

val localDrawableResources = staticCompositionLocalOf { DrawableResources() }
val localStringResources = staticCompositionLocalOf<IStringResources> {
    error("CompositionLocal IStringResources not present")
}

object Resources {
    val images: DrawableResources
        @Composable
        @ReadOnlyComposable
        get() = localDrawableResources.current

    val strings: IStringResources
        @Composable
        @ReadOnlyComposable
        get() = localStringResources.current

    val languageCode = mutableStateOf(LanguageCode.AR)

    fun switchLanguage(language: LanguageCode) {
        languageCode.value = language
    }
}