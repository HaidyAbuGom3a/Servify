package org.haidy.servify.presentation.screens.addPhoto

import org.haidy.servify.presentation.base.BaseUiEffect

sealed class AddPhotoUiEffect: BaseUiEffect {
    data class NavigateToVerifyEmail(val email: String): AddPhotoUiEffect()
    object NavigateUp: AddPhotoUiEffect()
    object ShowPickImageError: AddPhotoUiEffect()
    object ShowRequestFailed: AddPhotoUiEffect()
}