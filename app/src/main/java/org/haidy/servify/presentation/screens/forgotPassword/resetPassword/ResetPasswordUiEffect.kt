package org.haidy.servify.presentation.screens.forgotPassword.resetPassword

sealed class ResetPasswordUiEffect{
    object NavigateUp: ResetPasswordUiEffect()
    object NavigateToLogin: ResetPasswordUiEffect()
}
