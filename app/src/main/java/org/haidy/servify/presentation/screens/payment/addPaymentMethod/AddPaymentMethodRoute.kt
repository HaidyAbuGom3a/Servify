package org.haidy.servify.presentation.screens.payment.addPaymentMethod

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.haidy.servify.app.navigation.ServifyDestination

private const val ROUTE = ServifyDestination.ADD_PAYMENT_METHOD
fun NavController.navigateToAddPaymentMethod(){
    navigate(ROUTE)
}

fun NavGraphBuilder.addPaymentMethodRoute(){
    composable(ROUTE){ AddPaymentMethodScreen() }
}