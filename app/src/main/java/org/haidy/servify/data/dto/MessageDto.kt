package org.haidy.servify.data.dto

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class MessageDto(
    @ServerTimestamp
    val createdAt: Date? = null,
    val onPlatform: Boolean? = null,
    val participants: List<String>? = null,
    val senderId: String? = null,
    val text: String? = null,
    val user2: String? = null
)
