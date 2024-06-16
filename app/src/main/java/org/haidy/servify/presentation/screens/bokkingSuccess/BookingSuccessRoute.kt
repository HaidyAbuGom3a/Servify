package org.haidy.servify.presentation.screens.bokkingSuccess

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.haidy.servify.app.navigation.ServifyDestination


private const val ROUTE = ServifyDestination.BOOKING_SUCCESS
fun NavController.navigateToBookingSuccess(){
    navigate(ROUTE)
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.bookingSuccessRoute(){
    composable(ROUTE){ BookingSuccessScreen() }
}