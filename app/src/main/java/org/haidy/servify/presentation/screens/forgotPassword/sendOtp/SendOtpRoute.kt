package org.haidy.servify.presentation.screens.forgotPassword.sendOtp

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.haidy.servify.app.navigation.ServifyDestination

private const val ROUTE = ServifyDestination.SEND_OTP

fun NavController.navigateToSendOtp(clearBackStack: Boolean = false) {
    navigate(ROUTE) {
        if (clearBackStack) {
            popUpTo(graph.id) {
                inclusive = true
            }
        }
    }
}

fun NavGraphBuilder.sendOtpRoute() {
    composable(ROUTE) { SendOtpScreen() }
}