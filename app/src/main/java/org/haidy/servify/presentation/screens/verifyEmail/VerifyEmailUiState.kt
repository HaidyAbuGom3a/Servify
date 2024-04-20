package org.haidy.servify.presentation.screens.verifyEmail

import androidx.compose.ui.focus.FocusRequester
import org.haidy.servify.presentation.screens.forgotPassword.verifyCode.OtpDigits
import org.haidy.servify.presentation.screens.forgotPassword.verifyCode.TimerCountDown

data class VerifyEmailUiState(
    val isLoading: Boolean = false,
    val otp: String = "",
    val email: String = "",
    val digits: OtpDigits = OtpDigits(),
    val focusRequesters: List<FocusRequester> = listOf(
        FocusRequester(),
        FocusRequester(), FocusRequester(),
        FocusRequester()
    ),
    val timer: TimerCountDown = TimerCountDown()
)