package org.haidy.servify.presentation.screens.serviceSpecialists

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.haidy.servify.app.navigation.ServifyDestination
import org.haidy.servify.presentation.screens.serviceSpecialists.ServiceSpecialistsArgs.Companion.SERVICE_NAME

private const val ROUTE = ServifyDestination.SERVICE_SPECIALISTS
fun NavController.navigateToServiceSpecialists(serviceName: String, clearBackStack: Boolean = false) {
    navigate("$ROUTE/$serviceName") {
        if (clearBackStack) {
            popUpTo(graph.id) {
                inclusive = true
            }
        }
    }
}

fun NavGraphBuilder.serviceSpecialistsRoute() {
    composable(
        "$ROUTE/{${SERVICE_NAME}}",
        arguments = listOf(navArgument(SERVICE_NAME) { NavType.StringType })
    ) { ServiceSpecialistsScreen() }
}

class ServiceSpecialistsArgs(savedStateHandle: SavedStateHandle) {
    val serviceName: String = checkNotNull(savedStateHandle[SERVICE_NAME])

    companion object {
        const val SERVICE_NAME = "serviceName"
    }
}