package org.haidy.servify.presentation.screens.payment.paymentOption

import org.haidy.servify.domain.model.PaymentCard

data class PaymentOptionUiState(
    val cards: List<PaymentCard> = emptyList(),
    val selectedPaymentMethod: PaymentMethod? = null,
    val selectedCard: PaymentCard? = null
)

enum class PaymentMethod {
    CASH,
    CARD
}
