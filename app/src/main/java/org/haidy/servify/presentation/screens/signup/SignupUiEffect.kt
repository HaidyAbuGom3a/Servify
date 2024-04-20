package org.haidy.servify.presentation.screens.signup

import org.haidy.servify.presentation.base.BaseUiEffect

sealed class SignupUiEffect : BaseUiEffect {
    object NavigateUp : SignupUiEffect()
    object SingInWithGoogle : SignupUiEffect()
    object NavigateToHome : SignupUiEffect()
    object NavigateToLogin : SignupUiEffect()
    data class NavigateToAddPhoto(val email: String): SignupUiEffect()
    object ShowRequestFailed: SignupUiEffect()
}