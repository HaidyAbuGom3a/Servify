package org.haidy.servify.presentation.screens.bestSpecialists

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.haidy.servify.app.navigation.ServifyDestination

private const val ROUTE = ServifyDestination.SPECIALISTS
fun NavController.navigateToBestSpecialists(clearBackStack: Boolean = false) {
    navigate(ROUTE) {
        if (clearBackStack) {
            popUpTo(graph.id) {
                inclusive = true
            }
        }
    }
}

fun NavGraphBuilder.bestSpecialistsRoute() {
    composable(ROUTE) { BestSpecialistsScreen() }
}