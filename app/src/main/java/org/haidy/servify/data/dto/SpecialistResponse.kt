package org.haidy.servify.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpecialistResponse(
    @SerializedName("service_name")
    val serviceName: String? = null,
    @SerializedName("user_id")
    val userId: String? = null,
    @SerialName("specialist")
    val specialist: SpecialistDto? = null
)
