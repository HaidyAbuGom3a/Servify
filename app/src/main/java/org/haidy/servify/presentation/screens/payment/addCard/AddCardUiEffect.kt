package org.haidy.servify.presentation.screens.payment.addCard

sealed class AddCardUiEffect {
    object NavigateUp: AddCardUiEffect()
    object NavigateToAddPaymentMethod: AddCardUiEffect()
}