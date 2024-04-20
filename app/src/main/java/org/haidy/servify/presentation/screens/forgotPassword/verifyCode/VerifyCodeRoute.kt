package org.haidy.servify.presentation.screens.forgotPassword.verifyCode

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.haidy.servify.app.navigation.ServifyDestination

private const val ROUTE = ServifyDestination.VERIFY_CODE

fun NavController.navigateToVerifyCode(email: String, clearBackStack: Boolean = false) {
    navigate("$ROUTE/$email") {
        if (clearBackStack) {
            popUpTo(graph.id) {
                inclusive = true
            }
        }
    }
}

fun NavGraphBuilder.verifyCodeRoute() {
    composable("$ROUTE/{${ VerifyCodeArgs.EMAIL }}", arguments = listOf(
        navArgument(VerifyCodeArgs.EMAIL) { NavType.StringType }
    )) { VerifyCodeScreen() }
}


class VerifyCodeArgs(savedStateHandle: SavedStateHandle) {
    val email: String = checkNotNull(savedStateHandle[EMAIL])

    companion object {
        const val EMAIL = "email"
    }
}