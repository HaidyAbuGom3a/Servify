package org.haidy.servify.presentation.screens.bestSpecialists

import org.haidy.servify.domain.model.Specialist

data class SpecialistsUiState(
    val bestSpecialists: List<Specialist> = emptyList()
)
