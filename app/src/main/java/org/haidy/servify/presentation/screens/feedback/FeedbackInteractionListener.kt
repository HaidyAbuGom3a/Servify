package org.haidy.servify.presentation.screens.feedback

interface FeedbackInteractionListener {
    fun onClickSubmitFeedback()
    fun onClickBackIcon()
    fun onClickStar(index: Int)
    fun onFeedbackChanged(feedback: String)
}