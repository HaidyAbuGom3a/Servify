package org.haidy.servify.presentation.screens.profile

import android.graphics.Bitmap

interface ProfileInteractionListener {
    fun onClickBackIcon()
    fun onImagePicked(imageBitmap: Bitmap?)
}