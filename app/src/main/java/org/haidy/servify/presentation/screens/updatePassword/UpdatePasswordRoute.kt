package org.haidy.servify.presentation.screens.updatePassword

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.haidy.servify.app.navigation.ServifyDestination

const val ROUTE = ServifyDestination.UPDATE_PASSWORD

fun NavController.navigateToUpdatePassword(clearBackStack: Boolean = false) {
    navigate(ROUTE) {
        if (clearBackStack) {
            popUpTo(graph.id) {
                inclusive = true
            }
        }
    }
}

fun NavGraphBuilder.updatePasswordRoute() {
    composable(ROUTE) { UpdatePasswordScreen() }
}