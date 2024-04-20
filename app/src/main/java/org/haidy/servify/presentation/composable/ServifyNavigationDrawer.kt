package org.haidy.servify.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.presentation.modifier.noRippleEffect
import org.haidy.servify.presentation.screens.home.HomeInteractionListener

@Composable
fun ServifyNavigationDrawer(
    drawerState: DrawerState,
    username: String,
    numOfNotifications: Int = 0,
    listener: HomeInteractionListener,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .then(
                        if (drawerState.targetValue == DrawerValue.Open) Modifier.fillMaxSize() else Modifier
                    ),
                drawerShape = RectangleShape
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Theme.colors.drawer)
                        .padding(start = 24.dp)
                ) {
                    val scope = rememberCoroutineScope()
                    Icon(
                        painter = painterResource(id = Resources.images.closeIcon),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .noRippleEffect {
                                scope.launch {
                                    drawerState.close()
                                }
                            }
                    )
                    Text(
                        text = Resources.strings.helloUser,
                        style = Theme.typography.headline,
                        color = Color.White,
                        modifier = Modifier.padding(top = 120.dp)
                    )
                    val name =
                        if (LocalLayoutDirection.current == LayoutDirection.Ltr) "$username!" else "!$username"
                    Text(
                        text = name,
                        style = Theme.typography.headline,
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 44.dp)
                    )
                    val notificationIcon =
                        if (numOfNotifications > 0) Resources.images.notificationWithDotDrawerIcon else Resources.images.homeNotificationIcon
                    DrawerItem(
                        onClick = {
                            scope.launch {
                                drawerState.close()
                            }
                            listener.onClickNotifications()
                        },
                        text = Resources.strings.notifications,
                        icon = painterResource(
                            id = notificationIcon
                        ),
                        modifier = Modifier.padding(bottom = 24.dp),
                        numOfNotifications = numOfNotifications
                    )
                    DrawerItem(
                        onClick = {
                            scope.launch {
                                drawerState.close()
                            }
                            listener.onClickSettings()
                        },
                        text = Resources.strings.settings,
                        icon = painterResource(id = Resources.images.settingsDrawerIcon),
                        modifier = Modifier.padding(bottom = 24.dp)
                    )
                    DrawerItem(
                        onClick = {
                            scope.launch {
                                drawerState.close()
                            }
                            listener.onClickProfile()
                        },
                        text = Resources.strings.profile,
                        icon = painterResource(id = Resources.images.profileDrawerIcon),
                        modifier = Modifier.padding(bottom = 40.dp)
                    )

                    Divider(thickness = 1.dp, modifier = Modifier.width(154.dp))

                    Text(
                        text = Resources.strings.logout,
                        style = Theme.typography.bodyLarge,
                        color = Color.White,
                        modifier = Modifier
                            .padding(top = 40.dp)
                            .noRippleEffect {
                                scope.launch {
                                    drawerState.close()
                                }
                                listener.onClickLogout()
                            }
                    )

                }
            }
        },
        content = {
            content()
        }
    )
}


@Composable
private fun DrawerItem(
    onClick: () -> Unit,
    text: String,
    icon: Painter,
    modifier: Modifier = Modifier,
    numOfNotifications: Int = 0
) {
    Row(modifier.noRippleEffect { onClick() }, verticalAlignment = Alignment.CenterVertically) {
        val tint = if (numOfNotifications > 0) Color.Unspecified else Color.White
        Icon(icon, contentDescription = null, tint = tint)
        Text(
            text = text,
            style = Theme.typography.bodyLarge,
            color = Color.White,
            modifier = Modifier.padding(start = 8.dp, end = 40.dp)
        )
        if (numOfNotifications > 0) {
            Box(
                Modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(Theme.colors.error300),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = numOfNotifications.toString(),
                    style = Theme.typography.caption,
                    color = Color.White,
                )

            }
        }
    }
}
