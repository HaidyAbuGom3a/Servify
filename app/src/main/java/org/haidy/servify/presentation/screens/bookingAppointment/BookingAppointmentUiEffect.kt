package org.haidy.servify.presentation.screens.bookingAppointment

sealed class BookingAppointmentUiEffect{
    object NavigateUp: BookingAppointmentUiEffect()
    object NavigateToPaymentOption: BookingAppointmentUiEffect()
}