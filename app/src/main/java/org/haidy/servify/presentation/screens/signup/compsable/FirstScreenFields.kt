package org.haidy.servify.presentation.screens.signup.compsable

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.presentation.composable.ServifyTextField
import org.haidy.servify.presentation.screens.signup.SignupInteractionListener
import org.haidy.servify.presentation.screens.signup.SignupUiState

@Composable
fun ColumnScope.FirstScreenFields(state: SignupUiState, listener: SignupInteractionListener) {

    ServifyTextField(
        text = state.signUpForm.username,
        hint = Resources.strings.username,
        onValueChange = { listener.onUsernameChanged(it) },
        errorMessage = state.errors.usernameError
    )

    ServifyTextField(
        modifier = Modifier.padding(top = 16.dp),
        text =  state.signUpForm.email,
        hint = Resources.strings.email,
        onValueChange = { listener.onEmailChanged(it) },
        errorMessage = state.errors.emailError
    )

    ServifyTextField(
        modifier = Modifier.padding(top = 16.dp),
        text =  state.signUpForm.password,
        hint = Resources.strings.password,
        keyboardType = KeyboardType.Password,
        onValueChange = { listener.onPasswordChanged(it) },
        trailingPainter = painterResource(
            id = if ( state.signUpForm.passwordVisibility) Resources.images.disabledEyeIcon
            else Resources.images.eyeIcon
        ),
        onTrailingIconClick = { listener.onClickEyeIcon() },
        showPassword =  state.signUpForm.passwordVisibility,
        errorMessage = state.errors.passwordError,
    )

    ServifyTextField(
        modifier = Modifier.padding(top = 16.dp),
        text = state.signUpForm.confirmPassword,
        hint = Resources.strings.confirmPassword,
        keyboardType = KeyboardType.Password,
        onValueChange = { listener.onConfirmPasswordChanged(it) },
        trailingPainter = painterResource(
            id = if (state.signUpForm.confirmPasswordVisibility) Resources.images.disabledEyeIcon
            else Resources.images.eyeIcon
        ),
        onTrailingIconClick = { listener.onClickConfirmPasswordEyeIcon() },
        showPassword = state.signUpForm.confirmPasswordVisibility,
        errorMessage = state.errors.confirmPasswordError
    )
}