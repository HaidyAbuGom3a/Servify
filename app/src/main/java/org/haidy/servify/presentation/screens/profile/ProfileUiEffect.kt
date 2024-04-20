package org.haidy.servify.presentation.screens.profile

sealed class ProfileUiEffect{
    object NavigateUp: ProfileUiEffect()
    object ShowUpdatedSuccessfully: ProfileUiEffect()
    object ShowError: ProfileUiEffect()
}
