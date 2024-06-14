package org.haidy.servify.presentation.screens.payment.paymentOption

interface PaymentOptionInteractionListener {
    fun onClickBackIcon()
    fun onClickProceed()
    fun onClickMethod(index: Int)
}