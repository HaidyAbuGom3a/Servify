package org.haidy.servify.presentation.screens.updatePassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.presentation.composable.ServifyAppBar
import org.haidy.servify.presentation.composable.ServifyButton
import org.haidy.servify.presentation.composable.ServifyTextField
import org.haidy.servify.presentation.util.EffectHandler
import org.haidy.servify.presentation.util.rememberImeState

@Composable
fun UpdatePasswordScreen(viewModel: UpdatePasswordViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    EffectHandler(effects = viewModel.effect) { effect, navController ->
        when (effect) {
            UpdatePasswordUiEffect.NavigateUp -> navController.popBackStack()
        }
    }
    UpdatePasswordContent(state = state, listener = viewModel)
}

@Composable
fun UpdatePasswordContent(
    state: UpdatePasswordUiState,
    listener: UpdatePasswordInteractionListener
) {
    Scaffold(
        topBar = {
            ServifyAppBar(
                onNavigateUp = { listener.onClickBackIcon() },
                isBackIconVisible = true,
                title = Resources.strings.updatePassword
            )
        }
    ) { innerPadding ->
        val topPadding = innerPadding.calculateTopPadding()
        val bottomPadding = innerPadding.calculateBottomPadding()

        val imeState = rememberImeState()
        val scrollState = rememberScrollState()

        LaunchedEffect(key1 = imeState.value) {
            if (imeState.value) {
                scrollState.scrollTo(scrollState.maxValue)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Theme.colors.background)
                .padding(start = 24.dp, end = 24.dp, bottom = bottomPadding, top = topPadding)
                .verticalScroll(scrollState)
        ) {

            Spacer(modifier = Modifier.weight(1f))

            ServifyTextField(
                text = state.oldPassword,
                hint = Resources.strings.oldPassword,
                onValueChange = {
                    listener.onOldPasswordChanged(it)
                },
                keyboardType = KeyboardType.Password,
                trailingPainter = painterResource(
                    id = if (state.oldPasswordVisibility) Resources.images.disabledEyeIcon
                    else Resources.images.eyeIcon
                ),
                onTrailingIconClick = { listener.onClickOldPasswordEyeIcon() },
                showPassword = state.oldPasswordVisibility,
                errorMessage = state.errors.oldPasswordErrorMessage
            )

            ServifyTextField(
                text = state.newPassword,
                modifier = Modifier.padding(top = 16.dp),
                hint = Resources.strings.newPassword,
                onValueChange = {
                    listener.onNewPasswordChanged(it)
                },
                keyboardType = KeyboardType.Password,
                trailingPainter = painterResource(
                    id = if (state.newPasswordVisibility) Resources.images.disabledEyeIcon
                    else Resources.images.eyeIcon
                ),
                onTrailingIconClick = { listener.onClickNewPasswordEyeIcon() },
                showPassword = state.newPasswordVisibility,
                errorMessage = state.errors.newPasswordErrorMessage
            )

            ServifyTextField(
                modifier = Modifier.padding(top = 16.dp),
                text = state.confirmNewPassword,
                hint = Resources.strings.confirmNewPassword,
                onValueChange = {
                    listener.onConfirmNewPasswordChanged(it)
                },
                keyboardType = KeyboardType.Password,
                trailingPainter = painterResource(
                    id = if (state.confirmPasswordVisibility) Resources.images.disabledEyeIcon
                    else Resources.images.eyeIcon
                ),
                onTrailingIconClick = { listener.onClickConfirmNewPasswordEyeIcon() },
                showPassword = state.confirmPasswordVisibility,
                errorMessage = state.errors.confirmNewPasswordErrorMessage
            )

            Spacer(modifier = Modifier.weight(1f))

            ServifyButton(
                onClick = { listener.onClickConfirm() },
                text = Resources.strings.confirm,
                isLoading = state.isLoading,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}