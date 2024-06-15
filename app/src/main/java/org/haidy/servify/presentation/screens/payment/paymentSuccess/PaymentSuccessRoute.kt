package org.haidy.servify.presentation.screens.payment.paymentSuccess

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.haidy.servify.app.navigation.ServifyDestination

private const val ROUTE = ServifyDestination.PAYMENT_SUCCESS
fun NavController.navigateToPaymentSuccess(){
    navigate(ROUTE)
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.paymentSuccessRoute(){
    composable(ROUTE){ PaymentSuccessScreen() }
}