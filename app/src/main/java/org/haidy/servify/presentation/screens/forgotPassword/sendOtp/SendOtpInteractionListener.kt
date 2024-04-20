package org.haidy.servify.presentation.screens.forgotPassword.sendOtp

interface SendOtpInteractionListener {
    fun onEmailChanged(email: String)
    fun onClickSend()
    fun onClickBackIcon()
}