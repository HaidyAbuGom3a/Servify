package org.haidy.servify.presentation.screens.specialists

import org.haidy.servify.domain.model.Specialist

data class SpecialistsUiState(
    val bestSpecialists: List<Specialist> = emptyList()
)
