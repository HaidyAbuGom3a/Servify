package org.haidy.servify.presentation.screens.payment.addPaymentMethod

import org.haidy.servify.domain.model.PaymentCardType

data class AddPaymentMethodUiState(
    val cardHolder: String = "",
    val cardExpiryDate: String = "",
    val cardNumber: String = "",
    val cardType: PaymentCardType = PaymentCardType.UNKNOWN,
    val securityCode: String = "",
    val cardLogo: Int? = null
)
