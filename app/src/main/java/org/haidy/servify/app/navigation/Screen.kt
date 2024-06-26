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
        Screen(ServifyDestination.LOCATION, resources.directionIcon, resources.directionIcon)

    object BookingTrack :
        Screen(
            ServifyDestination.BOOKING_TRACK,
            resources.bookingIcon,
            resources.bookingIcon
        )

    object Chats :
        Screen(ServifyDestination.CHATS, resources.chatIcon, resources.chatIcon)

}