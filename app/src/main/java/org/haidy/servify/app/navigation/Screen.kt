package org.haidy.servify.app.navigation

import androidx.annotation.DrawableRes
import org.haidy.servify.app.resources.DrawableResources

val resources = DrawableResources()


sealed class Screen(
    val route: String,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
) {

    object Home : Screen(ServifyDestination.HOME, resources.homeIcon, resources.homeIcon)
    object Location :
        Screen(ServifyDestination.LOCATION, resources.locationIcon, resources.locationIcon)

    object BookingTrack :
        Screen(
            ServifyDestination.BOOKING_TRACK,
            resources.bookingIcon,
            resources.bookingIcon
        )

    object Profile :
        Screen(ServifyDestination.PROFILE, resources.profileIcon, resources.profileIcon)

}