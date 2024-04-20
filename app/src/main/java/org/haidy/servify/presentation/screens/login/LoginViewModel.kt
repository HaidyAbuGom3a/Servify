package org.haidy.servify.presentation.screens.login

import android.content.Intent
import android.content.IntentSender
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.haidy.servify.domain.model.AuthServiceProvider
import org.haidy.servify.domain.model.UserAuth
import org.haidy.servify.domain.usecase.AuthUseCase
import org.haidy.servify.domain.usecase.UserUseCase
import org.haidy.servify.domain.usecase.ValidationUseCase
import org.haidy.servify.presentation.base.BaseViewModel
import org.haidy.servify.presentation.base.ErrorState
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val manageAuth: AuthUseCase,
    private val manageUser: UserUseCase,
    private val manageValidation: ValidationUseCase
) :
    BaseViewModel<LoginUiState, LoginUiEffect>(LoginUiState()),
    LoginInteractionListener {


    override fun onEmailChanged(emailOrUsername: String) {
        _state.update { it.copy(email = emailOrUsername) }
    }

    override fun onPasswordChanged(password: String) {
        _state.update { it.copy(password = password) }
    }

    override fun onClickEyeIcon() {
        val visibility = _state.value.passwordVisibility
        _state.update { it.copy(passwordVisibility = !visibility) }
    }

    override fun onClickLogin() {
        val validationResult = validateInput()
        if (validationResult) {
            val state = _state.value
            _state.update {
                it.copy(
                    isLoading = true,
                    errors = it.errors.copy(emailErrorMessage = "", passwordErrorMessage = "")
                )
            }
            tryToExecute(
                { manageAuth.loginUser(state.email, state.password) },
                ::onLoginSuccess,
                ::onError
            )
        }
    }

    private fun validateInput(): Boolean {
        val state = state.value
        val emailValidation = manageValidation.validateEmail(state.email)
        val passwordValidation = manageValidation.validatePassword(state.password)
        val validationResult = emailValidation.isSuccessful && passwordValidation.isSuccessful
        if (!validationResult) {
            _state.update {
                it.copy(
                    errors = it.errors.copy(
                        emailErrorMessage = emailValidation.message,
                        passwordErrorMessage = passwordValidation.message
                    )
                )
            }
        }
        return validationResult
    }

    private fun onLoginSuccess(userAuth: UserAuth) {
        _state.update { it.copy(isLoading = false) }
        tryToExecute(
            { manageUser.saveToken(userAuth.token) },
            {
                tryToExecute(
                    { manageUser.saveEmail(state.value.email) },
                    { },
                    ::onError
                )
                tryToExecute(
                    { manageUser.savePassword(state.value.password) },
                    { },
                    ::onError
                )
            },
            ::onError
        )

        if (userAuth.isEmailVerified) {
            sendNewEffect(LoginUiEffect.NavigateToHome)
        } else {
            tryToExecute(
                { manageAuth.sendVerificationCode(state.value.email) },
                { sendNewEffect(LoginUiEffect.NavigateToVerifyEmail) },
                ::onError
            )
        }
        tryToExecute(
            { manageUser.saveIfEmailIsActive(userAuth.isEmailVerified) },
            { },
            ::onError
        )

    }

    private fun onError(e: ErrorState) {
        _state.update { it.copy(isLoading = false) }
        if (e is ErrorState.InvalidCredentials) {
            sendNewEffect(LoginUiEffect.ShowInvalidCredentialsMessage)
        } else {
            sendNewEffect(LoginUiEffect.ShowRequestFailed)
        }
    }

    override fun onClickForgotPassword() {
        sendNewEffect(LoginUiEffect.NavigateToForgotPassword)
    }

    override fun onClickSignUp() {
        sendNewEffect(LoginUiEffect.NavigateToSignUp)
    }

    override fun onClickLoginWithFacebook() {
        tryToExecute(
            { manageAuth.signInWithFacebook() },
            ::onSignInWithFacebookSuccess,
            ::onError
        )
    }

    private fun onSignInWithFacebookSuccess(result: Result<UserAuth>) {
        if (result.isSuccess) {
            val userAuth = result.getOrDefault(UserAuth("", false, AuthServiceProvider.EMAIL))
            tryToExecute(
                { manageUser.saveToken(result.getOrDefault(userAuth).token) },
                ::onSaveTokenFromLoginWithFacebookSuccess,
                ::onError
            )
        } else {
            sendNewEffect(LoginUiEffect.ShowRequestFailed)
        }
    }

    private fun onSaveTokenFromLoginWithFacebookSuccess(unit: Unit) {
        tryToExecute(
            { manageUser.saveAuthProvider(AuthServiceProvider.FACEBOOK.provider) },
            { },
            ::onError
        )
        tryToExecute(
            { manageUser.saveIfEmailIsActive(true) },
            { },
            ::onError
        )
        _state.update { it.copy(isLoading = false) }
        sendNewEffect(LoginUiEffect.NavigateToHome)
    }


    override fun onClickLoginWithGoogle() {
        sendNewEffect(LoginUiEffect.LoginWithGoogle)
    }

    fun onGoogleLoginWithIntentResult(result: Result<UserAuth>) {
        if (result.isSuccess) {
            val userAuth = result.getOrDefault(UserAuth("", false, AuthServiceProvider.GOOGLE))
            onLoginGoogleWithIntentSuccess(userAuth)
        } else {
            sendNewEffect(LoginUiEffect.ShowRequestFailed)
        }
    }

    private fun onLoginGoogleWithIntentSuccess(userAuth: UserAuth) {
        viewModelScope.launch {
            tryToExecute(
                { manageUser.saveToken(userAuth.token) },
                ::onSaveTokenFromLoginGoogleSuccess,
                ::onError
            )
            tryToExecute(
                { manageUser.saveIfEmailIsActive(userAuth.isEmailVerified) },
                { },
                ::onError
            )
        }
    }

    private fun onSaveTokenFromLoginGoogleSuccess(unit: Unit) {
        tryToExecute(
            { manageUser.saveAuthProvider(AuthServiceProvider.GOOGLE.provider) },
            { },
            ::onError
        )
        _state.update { it.copy(isLoading = false) }
        sendNewEffect(LoginUiEffect.NavigateToHome)
    }

    suspend fun signInWithGoogle(): IntentSender? {
        return manageAuth.signInWithGoogle()
    }

    suspend fun loginGoogleWithIntent(intent: Intent): Result<UserAuth> {
        return manageAuth.googleLoginWithIntent(intent)
    }


}