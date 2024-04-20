package org.haidy.servify.presentation.screens.forgotPassword.verifyCode

import androidx.compose.ui.focus.FocusRequester
import org.haidy.servify.app.utils.TimeFormat.timeFormat
import java.util.concurrent.TimeUnit

data class VerifyCodeUiState(
    val isLoading: Boolean = false,
    val otp: String = "",
    val digits: OtpDigits = OtpDigits(),
    val focusRequesters: List<FocusRequester> = listOf(
        FocusRequester(),
        FocusRequester(), FocusRequester(),
        FocusRequester()
    ),
    val timer: TimerCountDown = TimerCountDown()
)

data class OtpDigits(
    val firstDigit: String = "",
    val secondDigit: String = "",
    val thirdDigit: String = "",
    val fourthDigit: String = ""
)

val timeInMinutes = TimeUnit.MINUTES.toMillis(3)
val timeInSeconds = TimeUnit.SECONDS.toMillis(0)

data class TimerCountDown(
    val timeLeft: Long = timeInMinutes + timeInSeconds,
    val timerText: String = timeLeft.timeFormat()
)