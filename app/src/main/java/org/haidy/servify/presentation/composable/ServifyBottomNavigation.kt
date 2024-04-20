package org.haidy.servify.presentation.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import org.haidy.servify.app.LocalNavController
import org.haidy.servify.app.navigation.Screen
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.presentation.screens.bookingTrack.navigateToBookingTrack
import org.haidy.servify.presentation.screens.home.navigateToHome
import org.haidy.servify.presentation.screens.location.navigateToLocation
import org.haidy.servify.presentation.screens.profile.navigateToProfile

@Composable
fun ServifyBottomNavigation() {
    BottomAppBar(
        cutoutShape = CircleShape,
        backgroundColor = Theme.colors.card,
        elevation = 4.dp,
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
        contentPadding = PaddingValues(8.dp)
    ) {
        val navController = LocalNavController.current
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination?.route
        Screen.Home.let { home ->
            ServifyNavItem(
                item = home,
                isSelected = home.route == currentDestination,
                onClick = {
                    if (home.route != currentDestination) navController.navigateToHome()
                }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Screen.Location.let { location ->
            ServifyNavItem(
                item = location,
                isSelected = location.route == currentDestination,
                onClick = {
                    if (location.route != currentDestination) navController.navigateToLocation()
                }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Screen.BookingTrack.let { booking ->
            ServifyNavItem(
                item = booking,
                isSelected = booking.route == currentDestination,
                onClick = {
                    if (booking.route != currentDestination) navController.navigateToBookingTrack()
                }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Screen.Profile.let { profile ->
            ServifyNavItem(
                item = profile,
                isSelected = profile.route == currentDestination,
                onClick = {
                    if (profile.route != currentDestination) navController.navigateToProfile()
                }
            )
        }

    }
}

@Composable
@Preview
private fun Preview() {
    ServifyBottomNavigation()
}