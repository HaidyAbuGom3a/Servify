package org.haidy.servify.presentation.screens.bookingTrack

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.haidy.servify.app.navigation.ServifyDestination

private const val ROUTE = ServifyDestination.BOOKING_TRACK

fun NavController.navigateToBookingTrack(clearBackStack: Boolean = false) {
    navigate(ROUTE){
        if(clearBackStack){
            popUpTo(graph.id) {
                inclusive = true
            }
        }
    }
}

fun NavGraphBuilder.bookingTrackRoute(){
    composable(ROUTE){ BookingTrackScreen() }
}