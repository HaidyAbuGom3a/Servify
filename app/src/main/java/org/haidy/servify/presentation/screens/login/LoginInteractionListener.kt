package org.haidy.servify.presentation.screens.login

interface LoginInteractionListener {
    fun onEmailChanged(emailOrUsername: String)
    fun onPasswordChanged(password: String)
    fun onClickEyeIcon()
    fun onClickLogin()
    fun onClickForgotPassword()
    fun onClickSignUp()
    fun onClickLoginWithGoogle()
    fun onClickLoginWithFacebook()
}
