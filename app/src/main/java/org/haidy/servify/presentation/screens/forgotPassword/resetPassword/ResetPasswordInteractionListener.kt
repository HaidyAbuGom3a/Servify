package org.haidy.servify.presentation.screens.forgotPassword.resetPassword

interface ResetPasswordInteractionListener {
    fun onNewPasswordChanged(newPassword: String)
    fun onConfirmPasswordChanged(confirmPassword: String)
    fun onClickConfirm()
    fun onClickBackIcon()
    fun onClickNewPasswordEyeIcon()
    fun onClickConfirmPasswordEyeIcon()
}