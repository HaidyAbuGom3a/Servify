package org.haidy.servify.presentation.screens.bookingTrack

sealed class BookingTrackUiEffect{
    data class NavigateToFeedback(val specialistId: String): BookingTrackUiEffect()
    data class NavigateToScheduling(val orderId: String): BookingTrackUiEffect()
    object NavigateUp: BookingTrackUiEffect()
}