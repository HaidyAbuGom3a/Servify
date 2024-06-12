package org.haidy.servify.presentation.screens.bookingTrack

sealed class BookingTrackUiEffect{
    data class NavigateToFeedback(val specialistId: String): BookingTrackUiEffect()
    data class NavigateToScheduling(val specialistId: String, val orderId: String): BookingTrackUiEffect()
    data class NavigateToBookingCancellation(val orderId: String): BookingTrackUiEffect()
    object NavigateUp: BookingTrackUiEffect()
}