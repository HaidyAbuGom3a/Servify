package org.haidy.servify.presentation.screens.signup

import org.haidy.servify.domain.model.Gender

interface SignupInteractionListener {
    fun onUsernameChanged(username: String)
    fun onEmailChanged(email: String)
    fun onPasswordChanged(password: String)
    fun onPhoneNumberChanged(phone: String)
    fun onClickRegister()
    fun onClickBackIcon()
    fun onClickEyeIcon()
    fun onClickSignUpWithGoogle()
    fun onClickSignUpWithFacebook()
    fun onConfirmPasswordChanged(confirmPassword: String)
    fun onClickConfirmPasswordEyeIcon()
    fun onClickContinue()
    fun onClickGender(gender: Gender)
    fun onClickCountry(country: String)
    fun onClickGovernorate(governorate: String)
    fun onClickSignIn()
}