package org.haidy.servify.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
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
import org.haidy.servify.data.util.getCurrentTimeFormatted
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
        return userChatMessages.snapshots().map { chatMessagesSnapshot ->
            chatMessagesSnapshot.toObjects(MessageDto::class.java).map { it.toMessage() }
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
    override suspend fun sendMessage(senderId: String, receiverId: String, message: String) {
        val chatsCollectionRef = firestore.collection(CHATS)
        val chatId = "${senderId}_${receiverId}"
        val userChatDocumentRef = chatsCollectionRef.document(chatId)
        val userChatDocument = userChatDocumentRef.get().await()
        if (userChatDocument == null) createChat(senderId, receiverId, lastMessage = message)
        val messageData = MessageDto(
            createdAt = getCurrentTimeFormatted(),
            onPlatform = true,
            participants = listOf(senderId, receiverId),
            senderId = senderId,
            receiverId = receiverId,
            text = message
        )
        userChatDocumentRef.collection(MESSAGES).add(messageData).await()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun createChat(senderId: String, receiverId: String, lastMessage: String = "") {
        val userChatsCollectionRef = firestore.collection(USER_CHATS)
        val userChats = userChatsCollectionRef.document(senderId).get().await()
        val receiverChats = userChatsCollectionRef.document(receiverId).get().await()
        val newChat = UserChatHistoryDto(
            chatId = "${senderId}_${receiverId}",
            lastMessage = lastMessage,
            updatedAt = System.currentTimeMillis()
        )
        addChatToUser(userChatsCollectionRef, userChats, senderId, receiverId, newChat)
        addChatToUser(userChatsCollectionRef, receiverChats, receiverId, senderId, newChat)

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