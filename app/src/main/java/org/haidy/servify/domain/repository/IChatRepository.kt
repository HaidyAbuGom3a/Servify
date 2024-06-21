package org.haidy.servify.domain.repository

import kotlinx.coroutines.flow.Flow
import org.haidy.servify.domain.model.Message
import org.haidy.servify.domain.model.UserChats

interface IChatRepository {
    suspend fun sendMessage(chatId: String, senderId: String, receiverId: String, message: String)
    suspend fun getChatId(otherUserId: String): String
    suspend fun getChatHistory(): Flow<UserChats>
    suspend fun getMessages(chatId: String): Flow<List<Message>>
}