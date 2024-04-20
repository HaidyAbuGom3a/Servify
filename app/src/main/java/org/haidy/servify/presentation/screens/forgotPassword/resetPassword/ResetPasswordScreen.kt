package org.haidy.servify.presentation.screens.forgotPassword.resetPassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.presentation.composable.ServifyAppBar
import org.haidy.servify.presentation.composable.ServifyButton
import org.haidy.servify.presentation.composable.ServifyTextField
import org.haidy.servify.presentation.screens.login.navigateToLogin
import org.haidy.servify.presentation.util.EffectHandler
import org.haidy.servify.presentation.util.rememberImeState

@Composable
fun ResetPasswordScreen(viewModel: ResetPasswordViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    EffectHandler(effects = viewModel.effect) { effect, navController ->
        when (effect) {
            ResetPasswordUiEffect.NavigateToLogin -> navController.navigateToLogin(clearBackStack = true)
            ResetPasswordUiEffect.NavigateUp -> navController.popBackStack()
        }
    }
    ResetPasswordContent(state = state, listener = viewModel)

}

@Composable
fun ResetPasswordContent(state: ResetPasswordUiState, listener: ResetPasswordInteractionListener) {
    Scaffold(
        topBar = {
            ServifyAppBar(
                onNavigateUp = { listener.onClickBackIcon() },
                isBackIconVisible = true
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
            Text(
                text = Resources.strings.resetPassword,
                style = Theme.typography.headlineLarge,
                color = Theme.colors.dark100,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                text = Resources.strings.enterTheNewPassword,
                style = Theme.typography.bodyLarge,
                color = Theme.colors.dark200,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.weight(1f))

            ServifyTextField(
                modifier = Modifier.padding(bottom = 16.dp),
                text = state.newPassword,
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
                errorMessage = state.errorMessage
            )

            ServifyTextField(
                text = state.confirmPassword,
                hint = Resources.strings.confirmNewPassword,
                onValueChange = {
                    listener.onConfirmPasswordChanged(it)
                },
                keyboardType = KeyboardType.Password,
                trailingPainter = painterResource(
                    id = if (state.confirmPasswordVisibility) Resources.images.disabledEyeIcon
                    else Resources.images.eyeIcon
                ),
                onTrailingIconClick = { listener.onClickConfirmPasswordEyeIcon() },
                showPassword = state.confirmPasswordVisibility,
                errorMessage = state.errorMessage
            )

            Spacer(modifier = Modifier.weight(1f))

            ServifyButton(
                onClick = { listener.onClickConfirm() },
                text = Resources.strings.send,
                enabled = isButtonEnabled(
                    state.newPassword,
                    state.confirmPassword,
                    state.isLoading
                ),
                isLoading = state.isLoading,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

private fun isButtonEnabled(
    newPassword: String,
    confirmPassword: String,
    isLoading: Boolean
): Boolean {
    return newPassword == confirmPassword && !isLoading && newPassword.isNotEmpty()
            && confirmPassword.isNotEmpty()
}