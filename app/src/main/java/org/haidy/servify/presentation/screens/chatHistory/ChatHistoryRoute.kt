package org.haidy.servify.presentation.screens.chatHistory

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.haidy.servify.app.navigation.ServifyDestination

private const val ROUTE = ServifyDestination.CHATS

fun NavController.navigateToChatHistory(clearBackStack: Boolean = false) {
    navigate(ROUTE) {
        if (clearBackStack) {
            popUpTo(graph.id) {
                inclusive = true
            }
        }
    }
}

fun NavGraphBuilder.chatHistoryRoute() {
    composable(ROUTE) { ChatHistoryScreen() }
}