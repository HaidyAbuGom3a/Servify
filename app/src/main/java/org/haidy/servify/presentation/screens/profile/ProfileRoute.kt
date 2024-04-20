package org.haidy.servify.presentation.screens.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.haidy.servify.app.navigation.ServifyDestination

private const val ROUTE = ServifyDestination.PROFILE

fun NavController.navigateToProfile(clearBackStack: Boolean = false) {
    navigate(ROUTE){
        if(clearBackStack){
            popUpTo(graph.id) {
                inclusive = true
            }
        }
    }
}

fun NavGraphBuilder.profileRoute(){
    composable(ROUTE){ ProfileScreen() }
}