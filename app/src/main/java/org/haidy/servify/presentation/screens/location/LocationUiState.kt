package org.haidy.servify.presentation.screens.location

import org.haidy.servify.domain.model.Specialist

data class LocationUiState(
    val nearestSpecialists: List<Specialist> = emptyList(),
    val currentSpecialist: Specialist? = null
)