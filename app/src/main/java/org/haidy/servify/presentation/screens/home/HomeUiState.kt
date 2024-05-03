package org.haidy.servify.presentation.screens.home

import org.haidy.servify.domain.model.Service
import org.haidy.servify.domain.model.Specialist

data class HomeUiState(
    val userName: String = "",
    val userImageUrl: String = "",
    val offers: List<String> = emptyList(),
    val services: List<ServiceUiState> = emptyList(),
    val bestSpecialists: List<SpecialistUiState> = emptyList(),
    val isLoading: Boolean = false,
)

data class ServiceUiState(
    val id: String = "",
    val name: String = "",
    val imageUrl: String = "",
)

data class SpecialistUiState(
    val id: String = "",
    val name: String = "",
    val service: Service? = null,
    val country: String = "",
    val governorate: String = "",
    val rating: Double = 0.0
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

fun List<Specialist>.toUiState(): List<SpecialistUiState> {
    return map {
        SpecialistUiState(
            id = it.id,
            name = it.name,
            service = it.service,
            country = it.location.country,
            governorate = it.location.governorate,
            rating = it.rating
        )
    }
}