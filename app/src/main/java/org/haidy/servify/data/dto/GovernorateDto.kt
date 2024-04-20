package org.haidy.servify.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GovernorateDto(
    @SerialName("id")
    val id: Int?= null,
    @SerialName("name")
    val name: String?= null,
)
