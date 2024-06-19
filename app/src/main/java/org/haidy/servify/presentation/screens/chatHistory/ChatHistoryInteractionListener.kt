package org.haidy.servify.presentation.screens.chatHistory

interface ChatHistoryInteractionListener {
    fun onClickChat(otherUserId: String)
    fun onClickBackIcon()
}