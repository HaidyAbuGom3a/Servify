package org.haidy.servify.presentation.screens.updatePassword

interface UpdatePasswordInteractionListener {
    fun onClickBackIcon()
    fun onNewPasswordChanged(newPassword: String)
    fun onClickNewPasswordEyeIcon()
    fun onConfirmNewPasswordChanged(confirmNewPassword: String)
    fun onClickConfirmNewPasswordEyeIcon()
    fun onClickConfirm()
    fun onClickOldPasswordEyeIcon()
    fun onOldPasswordChanged(oldPassword: String)
}