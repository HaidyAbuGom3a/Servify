package org.haidy.servify.presentation.screens.bookingTrack

import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.utils.LocalizationManager

class StringResourceProvider {
    fun provide() = LocalizationManager.getStringResources(Resources.languageCode.value)
}

val stringResource = StringResourceProvider().provide()

enum class Tabs(val title: String) {
    UPCOMING(stringResource.upcoming),
    COMPLETED(stringResource.completed),
    CANCELED(stringResource.canceled);
}