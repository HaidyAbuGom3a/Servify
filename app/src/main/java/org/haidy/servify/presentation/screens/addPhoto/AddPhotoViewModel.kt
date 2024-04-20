package org.haidy.servify.presentation.screens.addPhoto

import android.graphics.Bitmap
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import org.haidy.servify.domain.usecase.AuthUseCase
import org.haidy.servify.domain.usecase.UserUseCase
import org.haidy.servify.presentation.base.BaseViewModel
import org.haidy.servify.presentation.base.ErrorState
import org.haidy.servify.presentation.screens.signup.SignUpFormUiState
import org.haidy.servify.presentation.screens.signup.toSignUpForm
import org.haidy.servify.presentation.screens.verifyEmail.VerifyEmailArgs
import javax.inject.Inject


@HiltViewModel
class AddPhotoViewModel @Inject constructor(
    private val manageAuth: AuthUseCase,
    savedStateHandle: SavedStateHandle
) :
    BaseViewModel<Bitmap?, AddPhotoUiEffect>(null),
    AddPhotoInteractionListener {

    private val args = VerifyEmailArgs(savedStateHandle)


    override fun onClickSkip(signUpForm: SignUpFormUiState) {
        tryToExecute(
            { manageAuth.signupUser(signUpForm.toSignUpForm(state.value)) },
            ::onRegisterSuccess,
            ::onError
        )
    }

    override fun onClickContinue(signUpForm: SignUpFormUiState) {
        if (state.value != null) {
            tryToExecute(
                { manageAuth.signupUser(signUpForm.toSignUpForm(state.value)) },
                ::onRegisterSuccess,
                ::onError
            )
        } else {
            sendNewEffect(AddPhotoUiEffect.ShowPickImageError)
        }
    }

    private fun onRegisterSuccess(value: Boolean) {
        sendNewEffect(AddPhotoUiEffect.NavigateToVerifyEmail(args.email))
    }

    private fun onError(e: ErrorState) {
        sendNewEffect(AddPhotoUiEffect.ShowRequestFailed)
    }

    override fun onClickBackIcon() {
        sendNewEffect(AddPhotoUiEffect.NavigateUp)
    }

    override fun onImagePicked(imageBitmap: Bitmap?) {
        _state.update { imageBitmap }
    }
}