package org.haidy.servify.presentation.screens.signup

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
import org.haidy.servify.presentation.composable.ServifyAppBar
import org.haidy.servify.presentation.composable.ServifyButton
import org.haidy.servify.presentation.modifier.noRippleEffect
import org.haidy.servify.presentation.screens.addPhoto.navigateToAddPhoto
import org.haidy.servify.presentation.screens.home.navigateToHome
import org.haidy.servify.presentation.screens.login.navigateToLogin
import org.haidy.servify.presentation.screens.signup.compsable.FirstScreenFields
import org.haidy.servify.presentation.screens.signup.compsable.SecondScreenFields
import org.haidy.servify.presentation.util.EffectHandler
import org.haidy.servify.presentation.util.rememberImeState

@Composable
fun SignupUpScreen(
    viewModel: SignupViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val scope = rememberCoroutineScope()
    val launcher = getLauncher(viewModel = viewModel, scope = scope)
    val context = LocalContext.current
    val requestFailedMessage = Resources.strings.requestFailed
    EffectHandler(effects = viewModel.effect) { effect, navController ->
        when (effect) {
            is SignupUiEffect.NavigateToAddPhoto -> {
                navController.navigateToAddPhoto(effect.email)
            }

            SignupUiEffect.NavigateToLogin -> navController.navigateToLogin(true)

            SignupUiEffect.NavigateUp -> {
                navController.popBackStack()
            }

            SignupUiEffect.SingInWithGoogle -> {
                scope.launch {
                    launcher.launch(
                        IntentSenderRequest.Builder(
                            viewModel.signInWithGoogle() ?: return@launch
                        ).build()
                    )
                }
            }

            SignupUiEffect.NavigateToHome -> navController.navigateToHome()
            SignupUiEffect.ShowRequestFailed -> {
                Toast.makeText(context, requestFailedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }
    SignUpContent(state, viewModel)
}

@Composable
fun SignUpContent(
    state: SignupUiState, listener: SignupInteractionListener
) {
    Scaffold(
        topBar = {
            ServifyAppBar(
                onNavigateUp = { listener.onClickBackIcon() },
                isBackIconVisible = true
            )
        }
    ) { paddingValues ->

        val imeState = rememberImeState()
        val scrollState = rememberLazyListState()
        LaunchedEffect(imeState.value) {
            if (imeState.value) {
                scrollState.animateScrollToItem(1)
            }
        }

        LazyColumn(
            modifier = Modifier
                .background(Theme.colors.background)
                .padding(top = paddingValues.calculateTopPadding(), start = 24.dp, end = 24.dp)
                .fillMaxSize(),
            state = scrollState,
            contentPadding = PaddingValues(vertical = 32.dp)
        ) {

            item {
                Text(
                    text = Resources.strings.signUp,
                    style = Theme.typography.headlineLarge,
                    color = Theme.colors.dark100,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 64.dp),
                    textAlign = TextAlign.Center
                )
            }

            item {
                AnimatedVisibility(
                    visible = state.isFirstContent,
                    exit = fadeOut(),
                    enter = fadeIn()
                ) {
                    Column {
                        FirstScreenFields(state, listener)
                    }
                }
            }

            item {
                AnimatedVisibility(
                    visible = !state.isFirstContent,
                    exit = fadeOut(),
                    enter = fadeIn()
                ) {
                    Column {
                        SecondScreenFields(state, listener)
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.padding(end = 4.dp),
                        text = Resources.strings.alreadyHaveAccount,
                        style = Theme.typography.bodyLarge,
                        color = Theme.colors.dark200
                    )
                    Text(
                        modifier = Modifier.noRippleEffect { listener.onClickSignIn() },
                        text = Resources.strings.signIn,
                        style = Theme.typography.bodyLarge,
                        color = Theme.colors.accent100
                    )
                }
            }

            item {
                val buttonText = if (state.isFirstContent)
                    Resources.strings.continueSteps else Resources.strings.signup

                val onClickButton = if (state.isFirstContent) {
                    { listener.onClickContinue() }
                } else {
                    { listener.onClickRegister() }
                }
                ServifyButton(
                    onClick = onClickButton,
                    text = buttonText,
                    modifier = Modifier.padding(top = 32.dp, bottom = 48.dp),
                    isLoading = state.isLoading,
                    enabled = !state.isLoading
                )
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    ServifyButton(
                        onClick = { listener.onClickSignUpWithGoogle() },
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
                            listener.onClickSignUpWithFacebook()
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
}

@Composable
private fun getLauncher(viewModel: SignupViewModel, scope: CoroutineScope) =
    rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                scope.launch(Dispatchers.IO) {
                    val signInResult = viewModel.signUpGoogleWithIntent(
                        intent = result.data ?: return@launch
                    )
                    viewModel.onGoogleSignUpWithIntentResult(signInResult)
                }
            }
        }
    )

@Preview(showSystemUi = false)
@Composable
private fun PreviewSingUp() {
    SignupUpScreen()
}