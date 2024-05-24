package org.haidy.servify.presentation.screens.bookingTrack

import org.haidy.servify.domain.model.ServiceOrder

data class BookingTrackUiState(
    val upcomingOrders: List<ServiceOrder> = emptyList(),
    val completedOrders: List<ServiceOrder> = emptyList(),
    val cancelledOrders: List<ServiceOrder> = emptyList()
)
