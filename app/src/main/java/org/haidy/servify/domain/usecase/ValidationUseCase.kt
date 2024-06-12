package org.haidy.servify.domain.usecase

import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.utils.LocalizationManager
import org.haidy.servify.domain.model.FormValidation
import org.haidy.servify.domain.model.PaymentCardType
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

    fun validateCardNumber(cardNumber: String): PaymentCardType {
        val sanitizedCardNumber = cardNumber.replace("\\s".toRegex(), "")

        return when {
            sanitizedCardNumber.isVisa() -> PaymentCardType.VISA
            sanitizedCardNumber.isMasterCard() -> PaymentCardType.MASTER_CARD
            sanitizedCardNumber.isAmericanExpress() -> PaymentCardType.AMERICAN_EXPRESS
            sanitizedCardNumber.isMeeza() -> PaymentCardType.MEEZA
            else -> PaymentCardType.UNKNOWN
        }
    }

    private fun String.isVisa(): Boolean {
        return this.matches(VISA_REGEX.toRegex()) && isValidLuhn(this)
    }

    private fun String.isMasterCard(): Boolean {
        return this.matches(MASTER_REGEX.toRegex()) && isValidLuhn(this)
    }

    private fun String.isAmericanExpress(): Boolean {
        return this.matches(AMEX_REGEX.toRegex()) && isValidLuhn(this)
    }

    private fun String.isMeeza(): Boolean {
        return this.matches(MEEZA_REGEX.toRegex()) && isValidLuhn(this)
    }

    private fun isValidLuhn(cardNumber: String): Boolean {
        var sum = 0
        var alternate = false
        for (i in cardNumber.length - 1 downTo 0) {
            var n = cardNumber[i].toString().toInt()
            if (alternate) {
                n *= 2
                if (n > 9) {
                    n -= 9
                }
            }
            sum += n
            alternate = !alternate
        }
        return sum % 10 == 0
    }

    companion object {
        const val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        const val PHONE_REGEX = "^(\\+201|01)[0-2,5]{1}[0-9]{8}$"
        const val VISA_REGEX = "^4[0-9]{12}(?:[0-9]{3})?$"
        const val MASTER_REGEX = "^5[1-5][0-9]{14}$"
        const val MEEZA_REGEX = "^50(?:[0-9]{14}|[0-9]{12})$"
        const val AMEX_REGEX = "^3[47][0-9]{13}$"
    }

}