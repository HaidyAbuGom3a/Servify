package org.haidy.servify.presentation.screens.login

import android.app.Activity.RESULT_OK
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.theme.Theme
import org.haidy.servify.presentation.composable.ServifyButton
import org.haidy.servify.presentation.composable.ServifyTextField
import org.haidy.servify.presentation.modifier.noRippleEffect
import org.haidy.servify.presentation.screens.forgotPassword.sendOtp.navigateToSendOtp
import org.haidy.servify.presentation.screens.home.navigateToHome
import org.haidy.servify.presentation.screens.signup.navigateToSignUp
import org.haidy.servify.presentation.screens.verifyEmail.navigateToVerifyEmail
import org.haidy.servify.presentation.util.EffectHandler
import org.haidy.servify.presentation.util.rememberImeState

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val scope = rememberCoroutineScope()
    val launcher = getLauncher(viewModel = viewModel, scope = scope)
    val context = LocalContext.current
    val invalidCredentialsMessage = Resources.strings.invalidCredentials
    val requestFailedMessage = Resources.strings.requestFailed
    EffectHandler(effects = viewModel.effect) { effects, navController ->
        when (effects) {
            LoginUiEffect.NavigateToForgotPassword -> navController.navigateToSendOtp()
            LoginUiEffect.NavigateToHome -> navController.navigateToHome(true)
            LoginUiEffect.NavigateToSignUp -> navController.navigateToSignUp()
            LoginUiEffect.LoginWithGoogle -> {
                scope.launch {
                    launcher.launch(
                        IntentSenderRequest.Builder(
                            viewModel.signInWithGoogle() ?: return@launch
                        ).build()
                    )
                }
            }

            LoginUiEffect.ShowInvalidCredentialsMessage -> {
                Toast.makeText(context, invalidCredentialsMessage, Toast.LENGTH_LONG).show()
            }

            LoginUiEffect.ShowRequestFailed -> {
                Toast.makeText(context, requestFailedMessage, Toast.LENGTH_LONG).show()
            }

            LoginUiEffect.NavigateToVerifyEmail -> navController.navigateToVerifyEmail(state.email)
        }
    }

    LoginContent(state, viewModel)
}

@Composable
fun LoginContent(state: LoginUiState, listener: LoginInteractionListener) {
    val imeState = rememberImeState()
    val scrollState = rememberLazyListState()

    LaunchedEffect(imeState.value) {
        if (imeState.value) {
            scrollState.animateScrollToItem(2)
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.background)
            .padding(horizontal = 24.dp),
        state = scrollState,
        contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp)
    ) {

        item {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Image(
                    modifier = Modifier.size(200.dp),
                    painter = painterResource(Resources.images.logo),
                    contentDescription = null
                )
            }
        }

        item {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = Resources.strings.welcomeBack,
                style = Theme.typography.headlineLarge,
                color = Theme.colors.dark100,
                textAlign = TextAlign.Center
            )
        }
        item {
            Text(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                text = Resources.strings.doYouHaveAccount,
                style = Theme.typography.bodyLarge,
                color = Theme.colors.dark200,
                textAlign = TextAlign.Center
            )
        }

        item {
            ServifyTextField(
                modifier = Modifier.padding(top = 32.dp),
                text = state.email,
                hint = Resources.strings.email,
                onValueChange = {
                    listener.onEmailChanged(it)
                },
                errorMessage = state.errors.emailErrorMessage,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
            )
        }

        item {
            ServifyTextField(
                modifier = Modifier.padding(top = 16.dp),
                text = state.password,
                hint = Resources.strings.password,
                keyboardType = KeyboardType.Password,
                onValueChange = {
                    listener.onPasswordChanged(it)
                },
                trailingPainter = painterResource(
                    id = if (state.passwordVisibility) Resources.images.disabledEyeIcon
                    else Resources.images.eyeIcon
                ),
                onTrailingIconClick = { listener.onClickEyeIcon() },
                showPassword = state.passwordVisibility,
                errorMessage = state.errors.passwordErrorMessage,
            )
        }

        item {
            Text(
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 32.dp)
                    .noRippleEffect { listener.onClickForgotPassword() },
                text = Resources.strings.forgotPassword,
                style = Theme.typography.bodyLarge,
                color = Theme.colors.blue
            )
        }

        item {
            ServifyButton(
                onClick = { listener.onClickLogin() },
                text = Resources.strings.login,
                modifier = Modifier.fillMaxWidth(),
                isLoading = state.isLoading,
                enabled = !state.isLoading && state.email.isNotEmpty() && state.password.isNotEmpty()
            )
        }

        item {
            Row(
                modifier = Modifier
                    .padding(vertical = 24.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(end = 4.dp),
                    text = Resources.strings.doNotHaveAccount,
                    style = Theme.typography.bodyLarge,
                    color = Theme.colors.dark200
                )
                Text(
                    modifier = Modifier.noRippleEffect { listener.onClickSignUp() },
                    text = Resources.strings.signup,
                    style = Theme.typography.bodyLarge,
                    color = Theme.colors.accent100
                )
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 32.dp, top = 8.dp),
            ) {
                ServifyButton(
                    onClick = { listener.onClickLoginWithGoogle() },
                    text = Resources.strings.google,
                    iconPainter = painterResource(id = Resources.images.googleIcon),
                    containerColor = Theme.colors.grey50,
                    contentColor = Theme.colors.dark100,
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.width(150.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                ServifyButton(
                    onClick = {
                        listener.onClickLoginWithFacebook()
                    },
                    text = Resources.strings.facebook,
                    iconPainter = painterResource(id = Resources.images.facebookIcon),
                    containerColor = Theme.colors.grey50,
                    contentColor = Theme.colors.dark100,
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.width(150.dp)
                )
            }

        }
    }
}

@Composable
private fun getLauncher(viewModel: LoginViewModel, scope: CoroutineScope) =
    rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == RESULT_OK) {
                scope.launch(Dispatchers.IO) {
                    val signInResult = viewModel.loginGoogleWithIntent(
                        intent = result.data ?: return@launch
                    )
                    viewModel.onGoogleLoginWithIntentResult(signInResult)
                }
            }
        }
    )


@Preview(showSystemUi = false)
@Composable
fun LoginPreview() {
    //LoginContent(state = LoginUiState())
}