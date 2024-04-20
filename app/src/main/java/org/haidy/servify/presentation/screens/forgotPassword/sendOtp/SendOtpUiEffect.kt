package org.haidy.servify.presentation.screens.forgotPassword.sendOtp

sealed class SendOtpUiEffect {
    data class NavigateToVerifyCode(val email: String) : SendOtpUiEffect()
    object NavigateUp: SendOtpUiEffect()
}
