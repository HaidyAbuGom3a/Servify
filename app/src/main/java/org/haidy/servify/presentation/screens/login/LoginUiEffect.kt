package org.haidy.servify.presentation.screens.login

sealed class LoginUiEffect {
    object NavigateToHome : LoginUiEffect()
    object NavigateToVerifyEmail : LoginUiEffect()
    object NavigateToForgotPassword : LoginUiEffect()
    object NavigateToSignUp : LoginUiEffect()
    object LoginWithGoogle: LoginUiEffect()
    object ShowInvalidCredentialsMessage: LoginUiEffect()
    object ShowRequestFailed: LoginUiEffect()
}
