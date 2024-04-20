package org.haidy.servify.presentation.screens.verifyEmail

interface VerifyEmailInteractionListener {
    fun onFirstOtpDigitChanged(digit: String)
    fun onSecondOtpDigitChanged(digit: String)
    fun onThirdOtpDigitChanged(digit: String)
    fun onFourthOtpDigitChanged(digit: String)
    fun onClickResendCode()
    fun onClickConfirm()
    fun onClickBackIcon()
}