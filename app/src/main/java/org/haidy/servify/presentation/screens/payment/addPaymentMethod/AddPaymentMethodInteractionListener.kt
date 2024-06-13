package org.haidy.servify.presentation.screens.payment.addPaymentMethod

interface AddPaymentMethodInteractionListener {
    fun onCardHolderChanged(cardHolder: String)
    fun onExpiryDateChanged(expiryDate: String)
    fun onSecurityCodeChanged(securityCode: String)
    fun onCardNumberChanged(cardNumber: String)
    fun onClickSave()
    fun onClickBackIcon()
}