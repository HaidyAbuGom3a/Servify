package org.haidy.servify.presentation.screens.bokkingSuccess

import org.haidy.servify.domain.model.ServiceOrder

data class BookingSuccessUiState(
    val customerName: String = "",
    val bookedOrder: ServiceOrder?= null
)
