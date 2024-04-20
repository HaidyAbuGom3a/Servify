package org.haidy.servify.presentation.screens.forgotPassword.verifyCode

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.presentation.composable.ServifyAppBar
import org.haidy.servify.presentation.composable.ServifyButton
import org.haidy.servify.presentation.modifier.noRippleEffect
import org.haidy.servify.presentation.screens.forgotPassword.resetPassword.navigateToResetPassword
import org.haidy.servify.presentation.screens.forgotPassword.verifyCode.composable.CodeTextField
import org.haidy.servify.presentation.util.EffectHandler
import org.haidy.servify.presentation.util.rememberImeState

@Composable
fun VerifyCodeScreen(viewModel: VerifyCodeViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    EffectHandler(effects = viewModel.effect) { effect, navController ->
        when (effect) {
            is VerifyCodeUiEffect.NavigateToResetPassword -> navController.navigateToResetPassword(
                effect.otp
            )

            VerifyCodeUiEffect.NavigateUp -> navController.popBackStack()
        }
    }
    VerifyCodeContent(state, viewModel)

}

@Composable
fun VerifyCodeContent(state: VerifyCodeUiState, listener: VerifyCodeInteractionListener) {
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

        LaunchedEffect(key1 = Unit) {
            state.focusRequesters[0].requestFocus()
        }

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
                text = Resources.strings.enterFourDigitCode,
                style = Theme.typography.bodyLarge,
                color = Theme.colors.dark200,
                textAlign = TextAlign.Center
            )

            Row(
                Modifier
                    .padding(vertical = 32.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CodeTextField(
                    text = state.digits.firstDigit,
                    onValueChanged = {
                        listener.onFirstOtpDigitChanged(it)
                    },
                    focusRequester = state.focusRequesters[0]
                )
                CodeTextField(
                    text = state.digits.secondDigit,
                    onValueChanged = {
                        listener.onSecondOtpDigitChanged(it)
                    },
                    focusRequester = state.focusRequesters[1]
                )
                CodeTextField(
                    text = state.digits.thirdDigit,
                    onValueChanged = {
                        listener.onThirdOtpDigitChanged(it)
                    },
                    focusRequester = state.focusRequesters[2]
                )
                CodeTextField(
                    text = state.digits.fourthDigit,
                    onValueChanged = {
                        listener.onFourthOtpDigitChanged(it)
                    },
                    focusRequester = state.focusRequesters[3]
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(start = 8.dp, end = 4.dp),
                    text = Resources.strings.theVerifyCodeWillExpireIn,
                    style = Theme.typography.bodyLarge,
                    color = Theme.colors.dark200
                )
                Text(
                    modifier = Modifier.padding(end = 8.dp),
                    text = state.timer.timerText,
                    style = Theme.typography.bodyLarge,
                    color = Theme.colors.dark200
                )
            }

            Row(
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = Resources.images.resendIcon),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .noRippleEffect { listener.onClickResetCode() },
                    text = Resources.strings.resendCode,
                    style = Theme.typography.bodyLarge.copy(fontSize = 16.sp),
                    color = Theme.colors.blue
                )
            }

            ServifyButton(
                onClick = { listener.onClickConfirm() },
                text = Resources.strings.confirm,
                enabled = isButtonEnabled(state.digits, state.isLoading),
                modifier = Modifier.padding(vertical = 24.dp).fillMaxWidth(),
                isLoading = state.isLoading
            )
        }
    }
}

private fun isButtonEnabled(digits: OtpDigits, isLoading: Boolean): Boolean {
    return digits.firstDigit.isNotEmpty() && digits.secondDigit.isNotEmpty() &&
            digits.thirdDigit.isNotEmpty() && digits.fourthDigit.isNotEmpty() && !isLoading
}