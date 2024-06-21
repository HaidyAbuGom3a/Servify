package org.haidy.servify.domain.model

data class Specialist(
    val id: String,
    val idAsUser: String = "",
    val name: String,
    val service: Service,
    val imageUrl: String,
    val location: Location,
    val rating: Double
)
