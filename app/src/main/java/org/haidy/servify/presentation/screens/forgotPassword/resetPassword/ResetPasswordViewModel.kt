package org.haidy.servify.presentation.screens.forgotPassword.resetPassword

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import org.haidy.servify.domain.usecase.AuthUseCase
import org.haidy.servify.domain.usecase.ValidationUseCase
import org.haidy.servify.presentation.base.BaseViewModel
import org.haidy.servify.presentation.base.ErrorState
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    @JvmSuppressWildcards private val manageAuth: AuthUseCase,
    private val manageValidation: ValidationUseCase,
    savedStateHandle: SavedStateHandle
) :
    BaseViewModel<ResetPasswordUiState, ResetPasswordUiEffect>(ResetPasswordUiState()),
    ResetPasswordInteractionListener {

    private val args = ResetPasswordArgs(savedStateHandle)

    override fun onNewPasswordChanged(newPassword: String) {
        _state.update { it.copy(newPassword = newPassword) }
    }

    override fun onConfirmPasswordChanged(confirmPassword: String) {
        _state.update { it.copy(confirmPassword = confirmPassword) }
    }

    override fun onClickConfirm() {
        val validationResult = manageValidation.validatePassword(state.value.newPassword)
        if(validationResult.isSuccessful){
            _state.update { it.copy(isLoading = true, errorMessage = "") }
            tryToExecute(
                { manageAuth.changePassword(newPassword = state.value.newPassword, otp = args.otp) },
                ::onResetPasswordSuccess,
                ::onError
            )
        }else{
            _state.update { it.copy(errorMessage = validationResult.message) }
        }
    }

    private fun onResetPasswordSuccess(unit: Unit) {
        _state.update { it.copy(isLoading = false) }
        sendNewEffect(ResetPasswordUiEffect.NavigateToLogin)
    }

    private fun onError(e: ErrorState) {
        _state.update { it.copy(isLoading = false) }
    }

    override fun onClickBackIcon() {
        sendNewEffect(ResetPasswordUiEffect.NavigateUp)
    }

    override fun onClickNewPasswordEyeIcon() {
        val visibility = _state.value.newPasswordVisibility
        _state.update { it.copy(newPasswordVisibility = !visibility) }
    }

    override fun onClickConfirmPasswordEyeIcon() {
        val visibility = _state.value.confirmPasswordVisibility
        _state.update { it.copy(confirmPasswordVisibility = !visibility) }
    }
}