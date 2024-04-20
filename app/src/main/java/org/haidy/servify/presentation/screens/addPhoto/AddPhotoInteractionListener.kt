package org.haidy.servify.presentation.screens.addPhoto

import android.graphics.Bitmap
import org.haidy.servify.presentation.screens.signup.SignUpFormUiState

interface AddPhotoInteractionListener {
    fun onClickSkip(signUpForm: SignUpFormUiState)
    fun onClickContinue(signUpForm: SignUpFormUiState)
    fun onClickBackIcon()
    fun onImagePicked(imageBitmap: Bitmap?)
}
