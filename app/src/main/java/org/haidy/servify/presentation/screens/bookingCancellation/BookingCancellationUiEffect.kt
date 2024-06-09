package org.haidy.servify.presentation.screens.bookingCancellation

sealed class BookingCancellationUiEffect {
    object NavigateUp: BookingCancellationUiEffect()
    object ShowSuccessMessage: BookingCancellationUiEffect()
}