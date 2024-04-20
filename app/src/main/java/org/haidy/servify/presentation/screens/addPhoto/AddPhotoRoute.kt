package org.haidy.servify.presentation.screens.addPhoto

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.haidy.servify.app.navigation.ServifyDestination

private const val ROUTE = ServifyDestination.ADD_PHOTO

fun NavController.navigateToAddPhoto(email: String, clearBackStack: Boolean = false) {
    navigate("$ROUTE/$email") {
        if (clearBackStack) {
            popUpTo(graph.id) {
                inclusive = true
            }
        }
    }
}

fun NavGraphBuilder.addPhotoRoute() {
    composable("$ROUTE/{${AddPhotoArgs.EMAIL}}", arguments = listOf(
        navArgument(AddPhotoArgs.EMAIL) { NavType.StringType }
    )) { AddPhotoScreen() }
}


class AddPhotoArgs(savedStateHandle: SavedStateHandle) {
    val email: String = checkNotNull(savedStateHandle[EMAIL])

    companion object {
        const val EMAIL = "email"
    }
}