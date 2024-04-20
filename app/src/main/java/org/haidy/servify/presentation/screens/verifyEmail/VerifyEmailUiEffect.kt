package org.haidy.servify.presentation.screens.verifyEmail

sealed class VerifyEmailUiEffect {
    object NavigateUp : VerifyEmailUiEffect()
    object NavigateToVerified : VerifyEmailUiEffect()
}
