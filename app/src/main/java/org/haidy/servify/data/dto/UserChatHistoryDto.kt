package org.haidy.servify.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class UserChatHistoryDto(
    @SerializedName("chatId")
    val chatId: String? = null,
    @SerializedName("lastMessage")
    val lastMessage: String? = null,
    @SerializedName("receiverId")
    val receiverId: String? = null,
    @SerializedName("updatedAt")
    val updatedAt: Long? = null
)
