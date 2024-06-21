package org.haidy.servify.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.haidy.servify.domain.model.Message
import org.haidy.servify.domain.model.UserChats
import org.haidy.servify.domain.repository.IChatRepository
import javax.inject.Inject

class ChatUseCase @Inject constructor(private val chatRepo: IChatRepository){
    suspend fun sendMessage(chatId: String, senderId: String, receiverId: String, message: String){
        chatRepo.sendMessage(chatId, senderId, receiverId, message)
    }
    suspend fun getChatId(otherUserId: String): String{
        return chatRepo.getChatId(otherUserId)
    }
    suspend fun getChatHistory(): Flow<UserChats>{
        return chatRepo.getChatHistory()
    }
    suspend fun getMessages(chatId: String): Flow<List<Message>>{
        return chatRepo.getMessages(chatId)
    }
}