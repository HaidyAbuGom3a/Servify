package org.haidy.servify.app

import android.annotation.SuppressLint
import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.HiltAndroidApp
import org.haidy.servify.app.navigation.ServifyDestination
import org.haidy.servify.app.navigation.ServifyNavGraph
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.app.utils.getIsDarkTheme
import org.haidy.servify.presentation.composable.GlowingCard
import org.haidy.servify.presentation.composable.ServifyBottomNavigation
import org.haidy.servify.presentation.screens.support.navigateToSupport

val LocalNavController =
    compositionLocalOf<NavHostController> { error("No NavController found!") }
val LocalPaddingValues = compositionLocalOf { PaddingValues(0.dp) }
val LocalDrawerState = compositionLocalOf<DrawerState> {
    error("No drawer state found!")
}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ServifyApp(state: MainUiState) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val isVisible = getIsNavigationBarVisible(navController = navController) && drawerState.isClosed
    val systemUiController = rememberSystemUiController()
    CompositionLocalProvider(
        LocalNavController provides navController,
        LocalDrawerState provides drawerState,
    ) {
        val useDarkIcons = !getIsDarkTheme(themeMode = Theme.themeMode.value)
        val statusBarColor = getStatusBarColor()
        println("currentDestination actual color: $statusBarColor")
        systemUiController.setStatusBarColor(statusBarColor, darkIcons = useDarkIcons)
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            scaffoldState = rememberScaffoldState(),
            backgroundColor = Theme.colors.background,
            floatingActionButtonPosition = FabPosition.Center,
            isFloatingActionButtonDocked = true,
            floatingActionButton = {
                if (isVisible) {
                    GlowingCard(
                        glowingColor = Theme.colors.accent100,
                        modifier = Modifier.size(56.dp),
                        onClick = { navController.navigateToSupport() },
                    ) {
                        Icon(
                            painter = painterResource(id = Resources.images.supportIcon),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            },
            bottomBar = {
                AnimatedVisibility(
                    isVisible,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Surface(
                        elevation = 16.dp,
                        color = Theme.colors.background,
                        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                    ) {
                        ServifyBottomNavigation()
                    }
                }
            }
        ) { paddingValues ->
            CompositionLocalProvider(LocalPaddingValues provides paddingValues) {
                val startDestination =
                    if (state.isFirstTimeUseApp) ServifyDestination.ON_BOARDING
                    else if (state.isLoggedIn) ServifyDestination.HOME else ServifyDestination.LOGIN
                ServifyNavGraph(startDestination = startDestination)
                //PaymentSuccessScreen()
            }

        }

    }
}


@HiltAndroidApp
class ServifyApplication : Application()


@Composable
private fun getIsNavigationBarVisible(navController: NavController): Boolean {
    return navController.currentBackStackEntryAsState().value?.destination?.route in listOf(
        ServifyDestination.HOME,
        ServifyDestination.LOCATION,
        ServifyDestination.BOOKING_TRACK,
        ServifyDestination.PROFILE
    )
}

@Composable
fun getStatusBarColor(): Color {
    val navBackStackEntry by LocalNavController.current.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route
    println("currentDestination: $currentDestination")
    val color = if (LocalDrawerState.current.isOpen) {
        Theme.colors.drawer
    } else if (currentDestination == ServifyDestination.PROFILE) {
        Theme.colors.primary300
    } else if (currentDestination == ServifyDestination.PAYMENT_SUCCESS) {
        Theme.colors.backgroundGrey
    } else {
        Theme.colors.background
    }
    println("currentDestination color: $color")
    return color
}