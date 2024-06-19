package org.haidy.servify.presentation.screens.chatHistory

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import org.haidy.servify.domain.usecase.ChatUseCase
import org.haidy.servify.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ChatHistoryViewModel @Inject constructor(
    private val chatUseCase: ChatUseCase
) :
    BaseViewModel<ChatHistoryUiState, ChatHistoryUiEffect>(ChatHistoryUiState()),
    ChatHistoryInteractionListener {

    init {
        getChats()
    }

    private fun getChats() {
        tryToCollect(
            { chatUseCase.getChatHistory() },
            { userChats -> _state.update { it.copy(chats = userChats.chats) } },
            {}
        )
    }

    override fun onClickChat(otherUserId: String) {
        sendNewEffect(ChatHistoryUiEffect.NavigateToChat(otherUserId))
    }

    override fun onClickBackIcon() {
        sendNewEffect(ChatHistoryUiEffect.NavigateUp)
    }
}