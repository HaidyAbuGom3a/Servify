package org.haidy.servify.domain.model

data class Message(
    val createdAt: Long,
    val senderId: String,
    val text: String,
    val receiverId: String
)
