package org.haidy.servify.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GovernoratesDto(
    @SerialName("country")
    val country: GovernorateDto? = null,
    @SerialName("governorate")
    val governorate: List<GovernorateDto>? = null
)
