package org.haidy.servify.presentation.screens.payment.addCard

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.haidy.servify.app.navigation.ServifyDestination

private const val ROUTE = ServifyDestination.ADD_CARD
fun NavController.navigateToAddCard(){
    navigate(ROUTE)
}

fun NavGraphBuilder.addCardRoute(){
    composable(ROUTE){ AddCardScreen() }
}