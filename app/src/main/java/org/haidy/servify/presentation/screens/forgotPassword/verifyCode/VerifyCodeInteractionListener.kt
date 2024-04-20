package org.haidy.servify.presentation.screens.forgotPassword.verifyCode

interface VerifyCodeInteractionListener {
    fun onFirstOtpDigitChanged(digit: String)
    fun onSecondOtpDigitChanged(digit: String)
    fun onThirdOtpDigitChanged(digit: String)
    fun onFourthOtpDigitChanged(digit: String)
    fun onClickResetCode()
    fun onClickConfirm()
    fun onClickBackIcon()
}