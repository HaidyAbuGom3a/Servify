package org.haidy.servify.presentation.screens.searchFilter

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.haidy.servify.app.navigation.ServifyDestination

private const val ROUTE = ServifyDestination.FILTER

fun NavController.navigateToFilter(){
    navigate(ROUTE)
}


fun NavGraphBuilder.filterRoute(){
    composable(ROUTE){ FilterScreen() }
}