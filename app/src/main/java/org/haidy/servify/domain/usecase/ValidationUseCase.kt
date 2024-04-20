package org.haidy.servify.domain.usecase

import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.utils.LocalizationManager
import org.haidy.servify.domain.model.FormValidation
import javax.inject.Inject


class ValidationUseCase @Inject constructor() {
    private val resource = LocalizationManager.getStringResources(Resources.languageCode.value)

    fun validateEmail(email: String): FormValidation {
        if (email.isEmpty()) {
            return FormValidation(isSuccessful = false, message = resource.emailIsRequired)
        }
        if (!EMAIL_REGEX.toRegex().matches(email)) {
            return FormValidation(isSuccessful = false, message = resource.invalidEmail)
        }
        return FormValidation(true)
    }

    fun validatePassword(password: String): FormValidation {
        if (password.isEmpty()) {
            return FormValidation(
                isSuccessful = false,
                message = resource.passwordIsRequired
            )
        }
        if (password.length < 8) {
            return FormValidation(isSuccessful = false, message = resource.invalidPassword)
        }
        return FormValidation(true)
    }

    fun validatePhoneNumber(phone: String): FormValidation {
        if (phone.isEmpty()) {
            return FormValidation(true)
        }
        if (!PHONE_REGEX.toRegex().matches(phone)) {
            return FormValidation(
                isSuccessful = false,
                message = resource.invalidPhoneNumber
            )
        }
        return FormValidation(true)
    }

    fun validateConfirmPassword(
        password: String,
        passwordConfirmation: String
    ): FormValidation {
        if (passwordConfirmation.isEmpty()) {
            return FormValidation(
                isSuccessful = false,
                message = resource.passwordIsRequired
            )
        }
        if (passwordConfirmation.length < 8) {
            return FormValidation(isSuccessful = false, message = resource.invalidPassword)
        }

        if (password != passwordConfirmation) {
            return FormValidation(
                isSuccessful = false,
                message = resource.passwordDoesNotMatch
            )
        }
        return FormValidation(true)
    }

    fun validateUsername(username: String): FormValidation {
        if (username.length < 5) {
            return FormValidation(
                isSuccessful = false,
                message = resource.usernameShouldBeAtLeastFiveLength
            )
        }
        return FormValidation(true)
    }

    fun validateGovernorate(governorate: String?): FormValidation {
        if (governorate.isNullOrEmpty()) {
            return FormValidation(false, resource.governorateIsRequired)
        }
        return FormValidation(true)
    }

    fun validateCountry(country: String?): FormValidation {
        if (country.isNullOrEmpty()) {
            return FormValidation(false, resource.countryIsRequired)
        }
        return FormValidation(true)
    }

    companion object {
        const val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        const val PHONE_REGEX = "^(\\+201|01)[0-2,5]{1}[0-9]{8}$"
    }

}