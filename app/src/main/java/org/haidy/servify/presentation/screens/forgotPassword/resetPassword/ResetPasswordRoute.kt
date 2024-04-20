package org.haidy.servify.presentation.screens.forgotPassword.resetPassword

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.haidy.servify.app.navigation.ServifyDestination

private const val ROUTE = ServifyDestination.RESET_PASSWORD

fun NavController.navigateToResetPassword(otp: String, clearBackStack: Boolean = false) {
    navigate("$ROUTE/$otp") {
        if (clearBackStack) {
            popUpTo(graph.id) {
                inclusive = true
            }
        }
    }
}

fun NavGraphBuilder.resetPasswordRoute() {
    composable(
        "$ROUTE/{${ResetPasswordArgs.OTP}}",
        arguments = listOf(navArgument(name = ResetPasswordArgs.OTP) { NavType.StringType })
    ) { ResetPasswordScreen() }
}

class ResetPasswordArgs(savedStateHandle: SavedStateHandle) {
    val otp: String = checkNotNull(savedStateHandle[OTP])

    companion object {
        const val OTP = "otp"
    }
}