package org.haidy.servify.presentation.screens.home

import org.haidy.servify.domain.model.Service
import org.haidy.servify.domain.model.Specialist

data class HomeUiState(
    val userName: String = "",
    val userImageUrl: String = "",
    val offers: List<String> = emptyList(),
    val services: List<ServiceUiState> = emptyList(),
    val bestSpecialists: List<Specialist> = emptyList(),
    val isLoading: Boolean = false,
)

data class ServiceUiState(
    val id: String = "",
    val name: String = "",
    val imageUrl: String = "",
)

fun List<Service>.toServiceUiState(): List<ServiceUiState> {
    return map {
        ServiceUiState(
            id = it.id,
            name = it.name,
            imageUrl = it.imageUrl
        )
    }
}