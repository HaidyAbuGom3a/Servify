package org.haidy.servify.presentation.screens.forgotPassword.sendOtp

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.presentation.composable.ServifyAppBar
import org.haidy.servify.presentation.composable.ServifyButton
import org.haidy.servify.presentation.composable.ServifyTextField
import org.haidy.servify.presentation.screens.forgotPassword.verifyCode.navigateToVerifyCode
import org.haidy.servify.presentation.util.EffectHandler
import org.haidy.servify.presentation.util.rememberImeState

@Composable
fun SendOtpScreen(viewModel: SendOtpViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    EffectHandler(effects = viewModel.effect) { effect, navController ->
        when (effect) {
            is SendOtpUiEffect.NavigateToVerifyCode -> navController.navigateToVerifyCode(effect.email)
            SendOtpUiEffect.NavigateUp -> navController.popBackStack()
        }
    }

    SendOtpContent(state, viewModel)

}

@Composable
fun SendOtpContent(state: SendOtpUiState, listener: SendOtpInteractionListener) {
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
                text = Resources.strings.forgetPassword,
                style = Theme.typography.headlineLarge,
                color = Theme.colors.dark100,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier
                    .padding(top = 16.dp, start = 8.dp, end = 8.dp)
                    .fillMaxWidth(),
                text = Resources.strings.enterYourEmailBelow,
                style = Theme.typography.bodyLarge,
                color = Theme.colors.dark200,
                textAlign = TextAlign.Center
            )

            ServifyTextField(
                modifier = Modifier.padding(vertical = 32.dp),
                text = state.email,
                hint = Resources.strings.email,
                onValueChange = {
                    listener.onEmailChanged(it)
                },
                errorMessage = state.errorMessage
            )

            Spacer(modifier = Modifier.weight(1f))

            ServifyButton(
                onClick = { listener.onClickSend() },
                text = Resources.strings.send,
                enabled = state.email.isNotEmpty() && !state.isLoading,
                modifier = Modifier.padding(vertical = 24.dp).fillMaxWidth(),
                isLoading = state.isLoading
            )
        }
    }
}
