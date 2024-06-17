package org.haidy.servify.presentation.screens.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.haidy.servify.app.navigation.ServifyDestination

private const val ROUTE = ServifyDestination.SEARCH
fun NavController.navigateToSearch(){
    navigate(ROUTE)
}

fun NavGraphBuilder.searchRoute(){
    composable(ROUTE){ SearchScreen() }
}