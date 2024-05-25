package org.haidy.servify.presentation.screens.bookingTrack

interface BookingTrackInteractionListener {
    fun onClickAddRating(specialistId: String)
    fun onClickReschedule(specialistId: String)
    fun onClickCancel(orderId: String)
    fun onClickReBook(specialistId: String)
    fun onClickBackIcon()
}