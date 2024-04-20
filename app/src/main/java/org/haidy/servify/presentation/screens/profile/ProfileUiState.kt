package org.haidy.servify.presentation.screens.profile

import android.graphics.Bitmap
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.utils.LocalizationManager
import org.haidy.servify.domain.model.Gender
import org.haidy.servify.domain.model.User

data class ProfileUiState(
    val username: String = "",
    val gender: Gender? = null,
    val email: String = "",
    val address: String = "",
    val phone: String = "",
    val country: String = "",
    val governorate: String = "",
    val imageUrl: String = "",
    val imageBitmap: Bitmap? = null,
    val isLoading: Boolean = false,
)


fun User.toUiState(): ProfileUiState {
    return ProfileUiState(
        username = username,
        email = email,
        phone = phoneNumber,
        country = location.country,
        governorate = location.governorate,
        imageUrl = imageUrl,
        gender = gender
    )
}