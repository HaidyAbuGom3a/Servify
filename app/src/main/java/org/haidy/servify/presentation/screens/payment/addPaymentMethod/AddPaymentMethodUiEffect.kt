package org.haidy.servify.presentation.screens.payment.addPaymentMethod

sealed class AddPaymentMethodUiEffect{
    object NavigateToHome: AddPaymentMethodUiEffect()
    object NavigateUp: AddPaymentMethodUiEffect()
}