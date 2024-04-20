package org.haidy.servify.domain.model

import android.graphics.Bitmap

data class FormSignUp(
    val username: String,
    val email: String,
    val password: String,
    val passwordConfirmation: String,
    val phoneNumber: String? = null,
    val imageBitmap: Bitmap? = null,
    val gender: Gender? = null,
    val countryId: Int? = null,
    val governorateId: Int? = null,
    val latitude: Double? = null,
    val longitude: Double? = null
)
