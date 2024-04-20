package org.haidy.servify.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.haidy.servify.app.LocalNavController

@Composable
fun <T> EffectHandler(
    effects: Flow<T>,
    handleEffect: (T, NavController) -> Unit
) {
    val scope = rememberCoroutineScope()
    val navController = LocalNavController.current
    LaunchedEffect(key1 = effects) {
        scope.launch {
            effects.collectLatest { effect ->
                handleEffect(effect, navController)
            }
        }
    }
}