package org.haidy.servify.presentation.screens.chat

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.haidy.servify.app.navigation.ServifyDestination

private const val ROUTE = ServifyDestination.CHAT

fun NavController.navigateToChat(otherUserId: String) {
    navigate("$ROUTE/$otherUserId")
}

fun NavGraphBuilder.chatRoute() {
    composable(
        "$ROUTE/{${ChatArgs.OTHER_USER_ID}}",
        arguments = listOf(navArgument(ChatArgs.OTHER_USER_ID) { NavType.StringType })
    ) { ChatScreen() }
}

class ChatArgs(savedStateHandle: SavedStateHandle) {
    val otherUserId: String = checkNotNull(savedStateHandle[OTHER_USER_ID])

    companion object {
        const val OTHER_USER_ID = "otherUserId"
    }
}