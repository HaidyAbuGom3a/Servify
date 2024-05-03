package org.haidy.servify.presentation.screens.services

import org.haidy.servify.presentation.screens.home.ServiceUiState

data class ServicesUiState(
    val services: List<ServiceUiState> = emptyList(),
    val isLoading: Boolean = false,
)
