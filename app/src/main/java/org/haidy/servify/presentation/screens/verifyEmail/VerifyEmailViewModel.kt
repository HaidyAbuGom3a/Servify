package org.haidy.servify.presentation.screens.verifyEmail

import android.os.CountDownTimer
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.haidy.servify.app.utils.TimeFormat.timeFormat
import org.haidy.servify.domain.usecase.AuthUseCase
import org.haidy.servify.domain.usecase.UserUseCase
import org.haidy.servify.presentation.base.BaseViewModel
import org.haidy.servify.presentation.base.ErrorState
import org.haidy.servify.presentation.screens.forgotPassword.verifyCode.timeInMinutes
import org.haidy.servify.presentation.screens.forgotPassword.verifyCode.timeInSeconds
import javax.inject.Inject

@HiltViewModel
class VerifyEmailViewModel @Inject constructor(
    private val manageAuth: AuthUseCase,
    private val manageUser: UserUseCase,
    savedStateHandle: SavedStateHandle
) :
    BaseViewModel<VerifyEmailUiState, VerifyEmailUiEffect>(VerifyEmailUiState()),
    VerifyEmailInteractionListener {

    private val args = VerifyEmailArgs(savedStateHandle)
    private var countDownTimer: CountDownTimer? = null

    private val initialTotalTimeInMillis = timeInMinutes + timeInSeconds
    private val countDownInterval = 1000L

    init {
        startCountDownTimer()
        updateEmail()
    }

    private fun updateEmail() {
        _state.update { it.copy(email = args.email) }
    }

    override fun onFirstOtpDigitChanged(digit: String) {
        val firstDigit = state.value.digits.firstDigit
        if (firstDigit.isEmpty()) {
            _state.update { it.copy(digits = it.digits.copy(firstDigit = digit)) }
            state.value.focusRequesters[1].requestFocus()
        }
        if (digit.isEmpty()) {
            _state.update { it.copy(digits = it.digits.copy(firstDigit = digit)) }
        }
    }

    override fun onSecondOtpDigitChanged(digit: String) {
        val secondDigit = state.value.digits.secondDigit
        if (secondDigit.isEmpty()) {
            _state.update { it.copy(digits = it.digits.copy(secondDigit = digit)) }
            state.value.focusRequesters[2].requestFocus()
        }
        if (digit.isEmpty()) {
            _state.update { it.copy(digits = it.digits.copy(secondDigit = digit)) }
        }
    }

    override fun onThirdOtpDigitChanged(digit: String) {
        val thirdDigit = state.value.digits.thirdDigit
        if (thirdDigit.isEmpty()) {
            _state.update { it.copy(digits = it.digits.copy(thirdDigit = digit)) }
            state.value.focusRequesters[3].requestFocus()
        }
        if (digit.isEmpty()) {
            _state.update { it.copy(digits = it.digits.copy(thirdDigit = digit)) }
        }
    }

    override fun onFourthOtpDigitChanged(digit: String) {
        val fourthDigit = state.value.digits.fourthDigit
        if (fourthDigit.isEmpty()) {
            _state.update { it.copy(digits = it.digits.copy(fourthDigit = digit)) }
        }
        if (digit.isEmpty()) {
            _state.update { it.copy(digits = it.digits.copy(fourthDigit = digit)) }
        }
    }

    override fun onClickResendCode() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            { manageAuth.sendVerificationCode(args.email) },
            ::onResendCodeSuccess,
            ::onError
        )
    }

    private fun onResendCodeSuccess(unit: Unit) {
        _state.update { it.copy(isLoading = false) }
        resetCountDownTimer()
        startCountDownTimer()
    }

    override fun onClickConfirm() {
        val digits = state.value.digits
        _state.update {
            it.copy(
                isLoading = true,
                otp = "${digits.firstDigit}${digits.secondDigit}${digits.thirdDigit}${digits.fourthDigit}"
            )
        }
        tryToExecute(
            { manageAuth.verifyEmail(state.value.otp) },
            ::onVerifyEmailSuccess,
            ::onError
        )
    }

    private fun onVerifyEmailSuccess(unit: Unit) {
        _state.update { it.copy(isLoading = false) }
        tryToExecute(
            { manageUser.saveIfEmailIsActive(true) },
            { sendNewEffect(VerifyEmailUiEffect.NavigateToVerified) },
            ::onError
        )
    }

    private fun onError(e: ErrorState) {
        _state.update { it.copy(isLoading = false) }
    }

    override fun onClickBackIcon() {
        sendNewEffect(VerifyEmailUiEffect.NavigateUp)
    }

    private fun startCountDownTimer() = viewModelScope.launch {
        countDownTimer = object : CountDownTimer(state.value.timer.timeLeft, countDownInterval) {
            override fun onTick(currentTimeLeft: Long) {
                _state.update {
                    it.copy(
                        timer = it.timer.copy(
                            timerText = currentTimeLeft.timeFormat(),
                            timeLeft = currentTimeLeft
                        )
                    )
                }
            }

            override fun onFinish() {
            }
        }.start()
    }

    private fun resetCountDownTimer() = viewModelScope.launch {
        countDownTimer?.cancel()
        _state.update {
            it.copy(
                timer = it.timer.copy(
                    timerText = initialTotalTimeInMillis.timeFormat(),
                    timeLeft = initialTotalTimeInMillis
                )
            )
        }
    }
}