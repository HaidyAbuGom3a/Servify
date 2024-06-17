package org.haidy.servify.presentation.screens.searchFilter

import org.haidy.servify.domain.model.Service

data class FilterUiState(
    val services: List<Service> = emptyList(),
    val selectedService: String = "",
    val selectedRating: String = "",
    val specialistName: String = ""
)
