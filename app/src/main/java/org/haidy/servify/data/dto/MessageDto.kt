package org.haidy.servify.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MessageDto(
    @SerializedName("createdAt")
    val createdAt: String? = null,
    @SerializedName("onPlatform")
    val onPlatform: Boolean? = null,
    @SerializedName("participants")
    val participants: List<String>? = null,
    @SerializedName("senderId")
    val senderId: String? = null,
    @SerializedName("text")
    val text: String? = null,
    @SerializedName("user2")
    val receiverId: String? = null
)
