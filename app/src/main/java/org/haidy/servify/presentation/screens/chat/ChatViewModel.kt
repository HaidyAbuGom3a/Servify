package org.haidy.servify.presentation.screens.chat

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.haidy.servify.domain.usecase.ChatUseCase
import org.haidy.servify.domain.usecase.UserUseCase
import org.haidy.servify.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatUseCase: ChatUseCase,
    private val userUseCase: UserUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<ChatUiState, ChatUiEffect>(ChatUiState()), ChatInteractionListener {
    private val args = ChatArgs(savedStateHandle)

    init {
        getUserId()
        getUsersData()
        getChatId()
    }


    private fun getUserId() {
        tryToExecute(
            { userUseCase.getUserId() },
            { userId ->
                _state.update { it.copy(userId = userId) }
            },
            {}
        )
    }

    private fun getChatId() {
        tryToExecute(
            { chatUseCase.getChatId(args.otherUserId) },
            { chatId ->
                val ids = listOf(state.value.userId, args.otherUserId).sorted()
                _state.update { it.copy(chatId = chatId.ifEmpty { "${ids[0]}_${ids[1]}" }) }
                getMessages()
            },
            {}
        )
    }

    private fun getUsersData() {
        viewModelScope.launch(Dispatchers.IO) {
            tryToExecute(
                { userUseCase.getUserProfile() },
                { user -> _state.update { it.copy(userImageUrl = user.imageUrl) } },
                {}
            )
            tryToExecute(
                { userUseCase.getUserData(args.otherUserId) },
                { otherUser ->
                    _state.update {
                        it.copy(
                            otherUserName = otherUser.name,
                            otherUserImageUrl = otherUser.userImageUrl
                        )
                    }
                },
                {}
            )
        }
    }

    private fun getMessages() {
        tryToCollect(
            { chatUseCase.getMessages(state.value.chatId) },
            { messages -> _state.update { it.copy(messages = messages) } },
            { }
        )
    }

    override fun onTextChanged(text: String) {
        _state.update { it.copy(text = text) }
    }

    override fun onClickSend() {
        tryToExecute(
            {
                val message = state.value.text
                _state.update { it.copy(text = "") }
                chatUseCase.sendMessage(
                    senderId = state.value.userId,
                    receiverId = args.otherUserId,
                    message = message,
                    chatId = state.value.chatId
                )
            },
            {},
            {}
        )
    }

    override fun onClickEmoji() {

    }

    override fun onClickBackIcon() {
        sendNewEffect(ChatUiEffect.NavigateUp)
    }
}