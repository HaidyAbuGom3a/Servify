package org.haidy.servify.presentation.screens.verifyEmail

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.haidy.servify.app.navigation.ServifyDestination

private const val ROUTE = ServifyDestination.VERIFY_EMAIL

fun NavController.navigateToVerifyEmail(email: String, clearBackStack: Boolean = false) {
    navigate("$ROUTE/$email") {
        if (clearBackStack) {
            popUpTo(graph.id) {
                inclusive = true
            }
        }
    }
}

fun NavGraphBuilder.verifyEmailRoute() {
    composable("$ROUTE/{${VerifyEmailArgs.EMAIL}}", arguments = listOf(
        navArgument(VerifyEmailArgs.EMAIL) { NavType.StringType }
    )) { VerifyEmailScreen() }
}


class VerifyEmailArgs(savedStateHandle: SavedStateHandle) {
    val email: String = checkNotNull(savedStateHandle[EMAIL])

    companion object {
        const val EMAIL = "email"
    }
}