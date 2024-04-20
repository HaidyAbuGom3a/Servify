package org.haidy.servify.presentation.screens.signup

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.haidy.servify.app.navigation.ServifyDestination

private const val ROUTE =  ServifyDestination.SIGNUP

fun NavController.navigateToSignUp(clearBackStack: Boolean = false) {
    navigate(ROUTE){
        if (clearBackStack) {
            popUpTo(graph.id) {
                inclusive = true
            }
        }
    }
}

fun NavGraphBuilder.signupScreenRoute() {
    composable(ROUTE) { SignupUpScreen() }
}