package org.haidy.servify.presentation.screens.verified

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.haidy.servify.app.navigation.ServifyDestination

private const val ROUTE = ServifyDestination.VERIFIED

fun NavController.navigateToVerified(clearBackStack: Boolean) {
    navigate(ROUTE) {
        if (clearBackStack) {
            popUpTo(graph.id) {
                inclusive = true
            }
        }
    }
}

fun NavGraphBuilder.verifiedRoute() {
    composable(ROUTE) { VerifiedScreen() }
}