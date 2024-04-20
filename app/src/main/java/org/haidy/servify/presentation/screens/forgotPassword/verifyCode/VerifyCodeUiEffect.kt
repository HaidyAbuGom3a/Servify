package org.haidy.servify.presentation.screens.forgotPassword.verifyCode

sealed class VerifyCodeUiEffect{
    object NavigateUp: VerifyCodeUiEffect()
    data class NavigateToResetPassword(val otp: String): VerifyCodeUiEffect()
}
