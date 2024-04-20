package org.haidy.servify.presentation.screens.forgotPassword.sendOtp

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import org.haidy.servify.domain.usecase.AuthUseCase
import org.haidy.servify.domain.usecase.ValidationUseCase
import org.haidy.servify.presentation.base.BaseViewModel
import org.haidy.servify.presentation.base.ErrorState
import javax.inject.Inject

@HiltViewModel
class SendOtpViewModel @Inject constructor(
    @JvmSuppressWildcards private val manageAuth: AuthUseCase,
    private val manageValidation: ValidationUseCase
) :
    BaseViewModel<SendOtpUiState, SendOtpUiEffect>(SendOtpUiState()),
    SendOtpInteractionListener {

    override fun onEmailChanged(email: String) {
        _state.update { it.copy(email = email) }
    }

    override fun onClickSend() {
        val validationResult = manageValidation.validateEmail(state.value.email)
        if(validationResult.isSuccessful){
            _state.update { it.copy(isLoading = true, errorMessage = "") }
            tryToExecute(
                { manageAuth.sendOtpToEmail(_state.value.email) },
                ::onSendOtpSuccess,
                ::onError
            )
        }
        else{
            _state.update { it.copy(errorMessage = validationResult.message) }
        }
    }

    override fun onClickBackIcon() {
        sendNewEffect(SendOtpUiEffect.NavigateUp)
    }

    private fun onSendOtpSuccess(isSuccess: Boolean) {
        _state.update { it.copy(isLoading = false) }
        sendNewEffect(SendOtpUiEffect.NavigateToVerifyCode(state.value.email))
    }

    private fun onError(e: ErrorState) {
        _state.update { it.copy(isLoading = false) }
    }


}