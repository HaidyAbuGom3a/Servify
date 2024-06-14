package org.haidy.servify.presentation.screens.payment.paymentOption

sealed class PaymentOptionUiEffect {
    object NavigateToPaymentSuccess: PaymentOptionUiEffect()
    object NavigateUp: PaymentOptionUiEffect()
}