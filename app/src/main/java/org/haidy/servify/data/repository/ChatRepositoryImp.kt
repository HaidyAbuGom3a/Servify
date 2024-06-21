package org.haidy.servify.data.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import org.haidy.servify.data.dto.MessageDto
import org.haidy.servify.data.dto.UserChatHistoryDto
import org.haidy.servify.data.dto.UserChatsDto
import org.haidy.servify.data.mapper.toMessage
import org.haidy.servify.data.mapper.toUserChatHistory
import org.haidy.servify.domain.model.Message
import org.haidy.servify.domain.model.UserChats
import org.haidy.servify.domain.repository.IChatRepository
import org.haidy.servify.domain.repository.IUserRepository
import javax.inject.Inject

class ChatRepositoryImp @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val userRepo: IUserRepository

) :
    IChatRepository {

    override suspend fun getMessages(chatId: String): Flow<List<Message>> {
        val userChatMessages = firestore.collection(CHATS).document(chatId).collection(MESSAGES)
            .orderBy("createdAt", Query.Direction.ASCENDING)
        return userChatMessages.snapshots().map { chatMessagesSnapshot ->
            val messages =
                chatMessagesSnapshot.toObjects(MessageDto::class.java).map { it.toMessage() }
            messages.sortedBy { it.createdAt }
        }.catch { emit(emptyList()) }
    }

    override suspend fun getChatHistory(): Flow<UserChats> {
        val userId = userRepo.getUserId()
        val userChats = firestore.collection(USER_CHATS).document(userId)
        return userChats.snapshots().map { chatHistorySnapshot ->
            val userChatDto = chatHistorySnapshot.toObject(UserChatsDto::class.java)
            UserChats(
                chats = userChatDto?.chats?.map {
                    val ids = it.chatId?.split("_").orEmpty()
                    val otherUserId = ids.find { id -> id != userId } ?: ""
                    val userData = userRepo.getUserData(otherUserId)
                    it.toUserChatHistory(userData.name, userData.userImageUrl)
                }.orEmpty()
            )
        }.catch { emit(UserChats(emptyList())) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun sendMessage(
        chatId: String,
        senderId: String,
        receiverId: String,
        message: String
    ) {
        val chatsCollectionRef = firestore.collection(CHATS)
        val ids = listOf(senderId, receiverId).sorted()
        val updatedChatId = chatId.ifEmpty { "${ids[0]}_${ids[1]}" }
        val messageData = MessageDto(
            onPlatform = true,
            participants = listOf(senderId, receiverId),
            senderId = senderId,
            user2 = receiverId,
            text = message
        )
        chatsCollectionRef.document(chatId).collection(MESSAGES).add(messageData).await()
        try {
            val userChatSnapshot = chatsCollectionRef.document(updatedChatId).collection(MESSAGES)
            val latestMessage = userChatSnapshot.get().await().documents.lastOrNull()
            val currentTimeStamp = latestMessage?.getTimestamp("createdAt")?.toDate()?.time ?: 0
            if (userChatSnapshot.get().await().size() > 1) {
                updateChat(
                    chatId,
                    message,
                    currentTimeStamp,
                    senderId,
                    receiverId,
                )
            } else {
                createChat(
                    chatId,
                    senderId,
                    receiverId,
                    currentTimeStamp,
                    lastMessage = message
                )
            }
        } catch (e: Exception) {
            Log.v("CHAT", "Error in trying to get chat document: $e")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun createChat(
        chatId: String,
        senderId: String,
        receiverId: String,
        lastUpdated: Long,
        lastMessage: String = ""
    ) {
        val userChatsCollectionRef = firestore.collection(USER_CHATS)
        val userChats = userChatsCollectionRef.document(senderId).get().await()
        val receiverChats = userChatsCollectionRef.document(receiverId).get().await()
        val newChat = UserChatHistoryDto(
            chatId = chatId,
            lastMessage = lastMessage,
            updatedAt = lastUpdated
        )
        addChatToUser(userChatsCollectionRef, userChats, senderId, receiverId, newChat)
        addChatToUser(userChatsCollectionRef, receiverChats, receiverId, senderId, newChat)
    }

    private suspend fun updateChat(
        chatId: String,
        lastMessage: String,
        lastUpdated: Long,
        senderId: String,
        receiverId: String
    ) {
        val userChatsCollectionRef = firestore.collection(USER_CHATS)
        val receiverDocument = userChatsCollectionRef.document(receiverId)
        val receiverChats = receiverDocument.get().await()
            .toObject(UserChatsDto::class.java)?.chats
        val senderDocument = userChatsCollectionRef.document(senderId)
        val senderChats = senderDocument.get().await()
            .toObject(UserChatsDto::class.java)?.chats

        val receiverUpdatedChats = receiverChats?.map { chatDto ->
            if (chatDto.chatId == chatId) chatDto.copy(
                lastMessage = lastMessage,
                updatedAt = lastUpdated
            ) else chatDto
        }

        val senderUpdatedChats = senderChats?.map { chatDto ->
            if (chatDto.chatId == chatId) chatDto.copy(
                lastMessage = lastMessage,
                updatedAt = lastUpdated
            ) else chatDto
        }
        senderDocument.set(UserChatsDto(senderUpdatedChats)).await()
        receiverDocument.set(UserChatsDto(receiverUpdatedChats)).await()
    }

    override suspend fun getChatId(otherUserId: String): String {
        val userId = userRepo.getUserId()
        val userChats = firestore.collection(USER_CHATS).document(userId)
        return userChats.get().await()
            .toObject(UserChatsDto::class.java)?.chats?.find { it.receiverId == otherUserId }?.chatId
            ?: ""
    }

    private suspend fun addChatToUser(
        userChatsCollectionRef: CollectionReference,
        userDocument: DocumentSnapshot?,
        userId: String,
        otherUserId: String,
        newChat: UserChatHistoryDto
    ) {
        val existingChats = userDocument?.toObject(UserChatsDto::class.java)?.chats.orEmpty()
        val updatedChats = existingChats + listOf(newChat.copy(receiverId = otherUserId))
        userChatsCollectionRef.document(userId).set(UserChatsDto(updatedChats)).await()
    }

    companion object {
        const val USER_CHATS = "userChats"
        const val CHATS = "chats"
        const val MESSAGES = "messages"
    }
}