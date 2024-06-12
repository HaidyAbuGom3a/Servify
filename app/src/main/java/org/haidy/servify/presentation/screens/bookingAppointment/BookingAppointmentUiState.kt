package org.haidy.servify.presentation.screens.bookingAppointment

import org.haidy.servify.domain.model.Specialist

data class BookingAppointmentUiState(
    val time: String = "",
    val day: String = "",
    val price: String = "",
    val requiredTasks: String = "",
    val specialist: Specialist?= null
)
