package org.haidy.servify.presentation.screens.signup

import android.graphics.Bitmap
import org.haidy.servify.domain.model.Country
import org.haidy.servify.domain.model.FormSignUp
import org.haidy.servify.domain.model.Gender
import org.haidy.servify.domain.model.Governorate

data class SignupUiState(
    val isFirstContent: Boolean = true,
    val isLoading: Boolean = false,
    val signUpForm: SignUpFormUiState = SignUpFormUiState(),
    val errors: SignupErrorMessage = SignupErrorMessage()
)

data class SignUpFormUiState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val passwordVisibility: Boolean = false,
    val confirmPassword: String = "",
    val confirmPasswordVisibility: Boolean = false,
    val phoneNumber: String = "",
    val genders: List<Gender> = listOf(Gender.MALE, Gender.FEMALE),
    val selectedGender: Gender? = null,
    val countries: List<Country> = emptyList(),
    val selectedCountry: String? = null,
    val governorates: List<Governorate> = emptyList(),
    val selectedGovernorate: String? = null,
)

data class SignupErrorMessage(
    val emailError: String = "",
    val passwordError: String = "",
    val phoneNumberError: String = "",
    val usernameError: String = "",
    val confirmPasswordError: String = "",
    val governorateError: String = "",
    val countryError: String = ""
)


fun SignUpFormUiState.toSignUpForm(imageBitmap: Bitmap?): FormSignUp {
    return FormSignUp(
        username = username,
        email = email,
        password = password,
        passwordConfirmation = confirmPassword,
        phoneNumber = phoneNumber,
        gender = selectedGender,
        countryId = countries.find { it.name == selectedCountry }?.id,
        governorateId = governorates.find { it.name == selectedGovernorate }?.id,
        latitude = 0.0,
        longitude = 0.0,
        imageBitmap = imageBitmap
    )
}