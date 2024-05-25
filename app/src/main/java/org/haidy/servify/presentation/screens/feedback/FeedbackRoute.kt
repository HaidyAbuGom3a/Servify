package org.haidy.servify.presentation.screens.feedback

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.haidy.servify.app.navigation.ServifyDestination

private const val ROUTE = ServifyDestination.FEEDBACK
fun NavController.navigateToFeedback(specialistId: String, clearBackStack: Boolean = false) {
    navigate("$ROUTE/$specialistId") {
        if (clearBackStack) {
            popUpTo(graph.id) {
                inclusive = true
            }
        }
    }
}

fun NavGraphBuilder.feedBackRoute() {
    composable("$ROUTE/{${FeedbackArgs.SPECIALIST_ID}}",
        arguments = listOf(
            navArgument(FeedbackArgs.SPECIALIST_ID) { NavType.StringType }
        )
    ) { FeedbackScreen() }
}

class FeedbackArgs(savedStateHandle: SavedStateHandle) {
    val id: String = checkNotNull(savedStateHandle[SPECIALIST_ID])

    companion object {
        const val SPECIALIST_ID = "SpecialistId"
    }
}