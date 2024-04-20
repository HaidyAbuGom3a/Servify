package org.haidy.servify.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MessageResponse(
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("message")
    val message: String? = null,
)
