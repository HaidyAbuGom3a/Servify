package org.haidy.servify.presentation.screens.payment.addCard

import org.haidy.servify.domain.model.PaymentCard

data class AddCardUiState(
    val cards: List<PaymentCard> = emptyList()
)