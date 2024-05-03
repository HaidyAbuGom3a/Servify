package org.haidy.servify.app

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.ServifyTheme
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.app.utils.CallbackManagerProvider
import org.haidy.servify.app.utils.ThemeMode
import org.haidy.servify.app.utils.getIsDarkTheme
import org.haidy.servify.presentation.screens.location.LocationScreen


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()
            CallbackManagerProvider.activity = this

            ServifyTheme(
                themeMode = Theme.themeMode.value,
                languageCode = Resources.languageCode.value
            ) {
                //SetTransparentStatusBar(Theme.themeMode.value)
                ServifyApp(state)
            }

        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        CallbackManagerProvider.callbackManager.onActivityResult(requestCode, resultCode, data)
        @Suppress("DEPRECATION")
        super.onActivityResult(requestCode, resultCode, data)
    }

    @Composable
    private fun SetTransparentStatusBar(themeMode: ThemeMode) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Theme.colors.background.toArgb()

        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = !getIsDarkTheme(themeMode = themeMode)
        }
    }


}