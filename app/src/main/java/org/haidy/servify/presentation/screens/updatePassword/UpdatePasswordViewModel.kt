package org.haidy.servify.presentation.screens.updatePassword

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import org.haidy.servify.domain.usecase.ValidationUseCase
import org.haidy.servify.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class UpdatePasswordViewModel @Inject constructor(
    private val validation: ValidationUseCase
) :
    BaseViewModel<UpdatePasswordUiState, UpdatePasswordUiEffect>(
        UpdatePasswordUiState()
    ), UpdatePasswordInteractionListener {
    override fun onClickBackIcon() {
        sendNewEffect(UpdatePasswordUiEffect.NavigateUp)
    }

    override fun onNewPasswordChanged(newPassword: String) {
        _state.update { it.copy(newPassword = newPassword) }
    }

    override fun onClickNewPasswordEyeIcon() {
        _state.update { it.copy(newPasswordVisibility = !state.value.newPasswordVisibility) }
    }

    override fun onConfirmNewPasswordChanged(confirmNewPassword: String) {
        _state.update { it.copy(confirmNewPassword = confirmNewPassword) }
    }

    override fun onClickConfirmNewPasswordEyeIcon() {
        _state.update { it.copy(confirmPasswordVisibility = !state.value.confirmPasswordVisibility) }
    }

    override fun onClickConfirm() {
        val isInputValid = validateInput()
        if (isInputValid) {
            _state.update {
                it.copy(
                    errors = it.errors.copy(
                        oldPasswordErrorMessage = "",
                        newPasswordErrorMessage = "",
                        confirmNewPasswordErrorMessage = ""
                    )
                )
            }
        }
    }

    override fun onClickOldPasswordEyeIcon() {
        _state.update { it.copy(oldPasswordVisibility = !state.value.oldPasswordVisibility) }
    }

    override fun onOldPasswordChanged(oldPassword: String) {
        _state.update { it.copy(oldPassword = oldPassword) }
    }

    private fun validateInput(): Boolean {
        val oldPasswordValidation = validation.validatePassword(state.value.oldPassword)
        val newPasswordValidation = validation.validatePassword(state.value.newPassword)
        val confirmNewPasswordValidation = validation.validateConfirmPassword(
            state.value.newPassword,
            state.value.confirmNewPassword
        )
        val isValid =
            oldPasswordValidation.isSuccessful && newPasswordValidation.isSuccessful &&
                    confirmNewPasswordValidation.isSuccessful
        _state.update {
            it.copy(
                errors = it.errors.copy(
                    oldPasswordErrorMessage = oldPasswordValidation.message,
                    newPasswordErrorMessage = newPasswordValidation.message,
                    confirmNewPasswordErrorMessage = confirmNewPasswordValidation.message
                )
            )
        }
        return isValid
    }
}