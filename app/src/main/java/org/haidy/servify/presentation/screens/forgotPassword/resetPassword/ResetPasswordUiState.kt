package org.haidy.servify.presentation.screens.forgotPassword.resetPassword

data class ResetPasswordUiState(
    val newPassword: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val newPasswordVisibility: Boolean = false,
    val confirmPasswordVisibility: Boolean = false,
)
