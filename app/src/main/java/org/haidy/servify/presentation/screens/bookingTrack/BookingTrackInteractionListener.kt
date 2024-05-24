package org.haidy.servify.presentation.screens.bookingTrack

interface BookingTrackInteractionListener {
    fun onClickAddRating(specialistId: String)
    fun onClickReschedule(orderId: String)
    fun onClickCancel(orderId: String)
    fun onClickReBook(specialistId: String)
    fun onClickBackIcon()
}