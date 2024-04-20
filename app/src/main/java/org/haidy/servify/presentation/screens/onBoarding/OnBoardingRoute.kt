package org.haidy.servify.presentation.screens.onBoarding

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.haidy.servify.app.navigation.ServifyDestination

private const val ROUTE = ServifyDestination.ON_BOARDING
fun NavGraphBuilder.onBoardingRoute() {
    composable(ROUTE) { OnBoardingScreen() }
}