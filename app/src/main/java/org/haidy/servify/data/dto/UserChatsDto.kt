package org.haidy.servify.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class UserChatsDto(
    @SerializedName("chats")
    val chats: List<UserChatHistoryDto>? = null
)
