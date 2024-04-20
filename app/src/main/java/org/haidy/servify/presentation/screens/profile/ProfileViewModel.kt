package org.haidy.servify.presentation.screens.profile

import android.graphics.Bitmap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import org.haidy.servify.domain.model.User
import org.haidy.servify.domain.usecase.UserUseCase
import org.haidy.servify.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) :
    BaseViewModel<ProfileUiState, ProfileUiEffect>(ProfileUiState()), ProfileInteractionListener {

    init {
        getUserProfile()
    }

    private fun getUserProfile() {
        tryToExecute(
            {
                _state.update { it.copy(isLoading = true) }
                userUseCase.getUserProfile()
            },
            ::onGetUserProfileSuccess,
            { _state.update { it.copy(isLoading = false) } }
        )
    }

    private fun onGetUserProfileSuccess(user: User) {
        _state.update { user.toUiState().copy(isLoading = false) }
    }

    override fun onClickBackIcon() {
        sendNewEffect(ProfileUiEffect.NavigateUp)
    }

    override fun onImagePicked(imageBitmap: Bitmap?) {
        if(imageBitmap == null) return
        _state.update { it.copy(imageBitmap = imageBitmap) }
        tryToExecute(
            { userUseCase.updateImage(imageBitmap) },
            { sendNewEffect(ProfileUiEffect.ShowUpdatedSuccessfully) },
            { sendNewEffect(ProfileUiEffect.ShowError) }
        )
    }
}