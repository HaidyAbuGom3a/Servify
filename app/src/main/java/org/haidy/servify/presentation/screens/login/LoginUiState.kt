package org.haidy.servify.presentation.screens.login

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val passwordVisibility: Boolean = false,
    val errors: LoginErrorMessage = LoginErrorMessage(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)

data class LoginErrorMessage(
    val emailErrorMessage: String = "",
    val passwordErrorMessage: String = ""
)