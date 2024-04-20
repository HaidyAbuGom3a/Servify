package org.haidy.servify.presentation.screens.updatePassword

data class UpdatePasswordUiState(
    val oldPassword: String = "",
    val newPassword: String = "",
    val confirmNewPassword: String = "",
    val isLoading: Boolean = false,
    val errors: UpdatePasswordError = UpdatePasswordError(),
    val oldPasswordVisibility: Boolean = false,
    val newPasswordVisibility: Boolean = false,
    val confirmPasswordVisibility: Boolean = false,
)

data class UpdatePasswordError(
    val oldPasswordErrorMessage: String = "",
    val newPasswordErrorMessage: String = "",
    val confirmNewPasswordErrorMessage: String = "",
)