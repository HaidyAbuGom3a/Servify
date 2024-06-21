package org.haidy.servify.presentation.screens.chat

interface ChatInteractionListener {
    fun onTextChanged(text: String)
    fun onClickSend()
    fun onClickEmoji()
    fun onClickBackIcon()
}