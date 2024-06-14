package org.haidy.servify.presentation.screens.payment.paymentOption

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.haidy.servify.app.navigation.ServifyDestination

private const val ROUTE = ServifyDestination.PAYMENT_OPTION
fun NavController.navigateToPaymentOption(){
    navigate(ROUTE)
}

fun NavGraphBuilder.paymentOptionRoute(){
    composable(ROUTE) { PaymentOptionScreen() }
}