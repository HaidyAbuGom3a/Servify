package org.haidy.servify.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpecialistDto(
    @SerialName("id")
    val id: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("image")
    val image: String? = null,
    @SerialName("status")
    val status: String? = null,
    @SerialName("rating")
    val rating: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("location")
    val location: UserLocationDto? = null
)
