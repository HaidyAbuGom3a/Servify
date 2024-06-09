package org.haidy.servify.presentation.screens.bookingCancellation

interface BookingCancellationInteractionListener {
    fun onReasonChanged(reason: String)
    fun onClickConfirm()
    fun onClickReason(reason: String)
    fun onClickBack()
}