package org.haidy.servify.presentation.screens.forgotPassword.sendOtp

data class SendOtpUiState(
    val email: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String = ""
)
