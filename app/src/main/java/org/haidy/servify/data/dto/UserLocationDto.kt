package org.haidy.servify.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserLocationDto(
    @SerialName("country")
    val country: String? = null,
    @SerialName("governorate")
    val governorate: String? = null,
    @SerialName("latitude")
    val latitude: Double? = null,
    @SerialName("longitude")
    val longitude: Double? = null
)