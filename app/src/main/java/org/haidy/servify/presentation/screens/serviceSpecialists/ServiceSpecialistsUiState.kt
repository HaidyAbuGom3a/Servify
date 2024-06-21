package org.haidy.servify.presentation.screens.serviceSpecialists

import org.haidy.servify.domain.model.Specialist

data class  ServiceSpecialistsUiState(
    val specialists: List<Specialist> = emptyList(),
    val serviceName: String = ""
)
