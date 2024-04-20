package org.haidy.servify.presentation.screens.home

sealed class HomeUiEffect{
    object NavigateToProfile : HomeUiEffect()
    object NavigateToSettings : HomeUiEffect()
    object NavigateToNotifications : HomeUiEffect()
    object NavigateToLogin : HomeUiEffect()
}
