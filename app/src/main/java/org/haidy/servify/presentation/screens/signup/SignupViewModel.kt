package org.haidy.servify.presentation.screens.signup

import android.content.Intent
import android.content.IntentSender
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.haidy.servify.domain.model.AuthServiceProvider
import org.haidy.servify.domain.model.Country
import org.haidy.servify.domain.model.Gender
import org.haidy.servify.domain.model.UserAuth
import org.haidy.servify.domain.usecase.AuthUseCase
import org.haidy.servify.domain.usecase.LocationUseCase
import org.haidy.servify.domain.usecase.UserUseCase
import org.haidy.servify.domain.usecase.ValidationUseCase
import org.haidy.servify.presentation.base.BaseViewModel
import org.haidy.servify.presentation.base.ErrorState
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val manageAuth: AuthUseCase,
    private val manageUser: UserUseCase,
    private val manageValidation: ValidationUseCase,
    private val locationUseCase: LocationUseCase
) :
    BaseViewModel<SignupUiState, SignupUiEffect>(SignupUiState()), SignupInteractionListener {


    init {
        getAllCountries()
    }

    private fun getAllCountries() {
        tryToExecute(
            { locationUseCase.getCountries() },
            ::onGetAllCountriesSuccess,
            ::onError
        )
    }

    private fun onGetAllCountriesSuccess(countries: List<Country>) {
        _state.update {
            it.copy(signUpForm = it.signUpForm.copy(countries = countries))
        }
    }

    override fun onUsernameChanged(username: String) {
        _state.update {
            it.copy(signUpForm = it.signUpForm.copy(username = username))
        }
    }

    override fun onEmailChanged(email: String) {
        _state.update {
            it.copy(signUpForm = it.signUpForm.copy(email = email))
        }
    }

    override fun onPasswordChanged(password: String) {
        _state.update {
            it.copy(signUpForm = it.signUpForm.copy(password = password))
        }
    }

    override fun onPhoneNumberChanged(phone: String) {
        _state.update {
            it.copy(signUpForm = it.signUpForm.copy(phoneNumber = phone))
        }
    }

    override fun onClickRegister() {
        val state = state.value
        val validationResult = validateSecondContentInput()
        if (validationResult) {
            _state.update {
                it.copy(errors = it.errors.copy(phoneNumberError = ""))
            }
            sendNewEffect(SignupUiEffect.NavigateToAddPhoto(state.signUpForm.email))
        }

    }

    private fun validateFirstContentInput(): Boolean {
        val state = _state.value.signUpForm
        val emailValidation = manageValidation.validateEmail(state.email)
        val passwordValidation = manageValidation.validatePassword(state.password)
        val confirmPasswordValidation = manageValidation.validateConfirmPassword(
            password = state.password,
            passwordConfirmation = state.confirmPassword
        )
        val usernameValidation = manageValidation.validateUsername(state.username)
        val validationResult =
            emailValidation.isSuccessful && passwordValidation.isSuccessful && usernameValidation.isSuccessful
                    && confirmPasswordValidation.isSuccessful
        if (!validationResult) {
            _state.update {
                it.copy(
                    errors = it.errors.copy(
                        emailError = emailValidation.message,
                        passwordError = passwordValidation.message,
                        usernameError = usernameValidation.message,
                        confirmPasswordError = confirmPasswordValidation.message
                    )
                )
            }
        }
        return validationResult
    }

    private fun validateSecondContentInput(): Boolean {
        val state = _state.value.signUpForm
        val phoneValidation = manageValidation.validatePhoneNumber(state.phoneNumber)
        val governorateValidation = manageValidation.validateGovernorate(state.selectedGovernorate)
        val countryValidation = manageValidation.validateCountry(state.selectedCountry)
        val validation =
            phoneValidation.isSuccessful && governorateValidation.isSuccessful && countryValidation.isSuccessful
        if (validation) {
            _state.update {
                it.copy(
                    errors = it.errors.copy(
                        phoneNumberError = phoneValidation.message,
                        governorateError = governorateValidation.message,
                        countryError = countryValidation.message
                    )
                )
            }
        }
        return validation
    }

    private fun onError(e: ErrorState) {
        _state.update { it.copy(isLoading = false) }
        sendNewEffect(SignupUiEffect.ShowRequestFailed)
    }

    override fun onClickBackIcon() {
        if (state.value.isFirstContent) {
            sendNewEffect(SignupUiEffect.NavigateToLogin)
        } else {
            _state.update { it.copy(isFirstContent = true) }
        }
    }

    override fun onClickEyeIcon() {
        val visibility = _state.value.signUpForm.passwordVisibility
        _state.update { it.copy(signUpForm = it.signUpForm.copy(passwordVisibility = !visibility)) }
    }


    override fun onClickSignUpWithGoogle() {
        sendNewEffect(SignupUiEffect.SingInWithGoogle)
    }

    suspend fun signInWithGoogle(): IntentSender? {
        return manageAuth.signInWithGoogle()
    }

    suspend fun signUpGoogleWithIntent(intent: Intent): Result<UserAuth> {
        return manageAuth.googleSignUpWithIntent(intent)
    }

    fun onGoogleSignUpWithIntentResult(result: Result<UserAuth>) {
        if (result.isSuccess) {
            val userAuth = result.getOrDefault(UserAuth("", false, AuthServiceProvider.GOOGLE))
            onLoginGoogleWithIntentSuccess(userAuth)
        } else {
            sendNewEffect(SignupUiEffect.ShowRequestFailed)
        }
    }

    private fun onLoginGoogleWithIntentSuccess(userAuth: UserAuth) {
        viewModelScope.launch {
            tryToExecute(
                { manageUser.saveToken(userAuth.token) },
                ::onSaveTokenFromSignInWithGoogleSuccess,
                ::onError
            )
            tryToExecute(
                { manageUser.saveIfEmailIsActive(userAuth.isEmailVerified) },
                { },
                ::onError
            )
        }
    }

    private fun onSaveTokenFromSignInWithGoogleSuccess(unit: Unit) {
        tryToExecute(
            { manageUser.saveAuthProvider(AuthServiceProvider.GOOGLE.provider) },
            {
                _state.update { it.copy(isLoading = false) }
                sendNewEffect(SignupUiEffect.NavigateToHome)
            },
            ::onError
        )
    }

    override fun onClickSignUpWithFacebook() {
        tryToExecute(
            { manageAuth.signInWithFacebook() },
            ::onSignInWithFacebookSuccess,
            ::onError
        )
    }

    override fun onConfirmPasswordChanged(confirmPassword: String) {
        _state.update {
            it.copy(signUpForm = it.signUpForm.copy(confirmPassword = confirmPassword))
        }
    }

    override fun onClickConfirmPasswordEyeIcon() {
        val visibility = _state.value.signUpForm.confirmPasswordVisibility
        _state.update { it.copy(signUpForm = it.signUpForm.copy(confirmPasswordVisibility = !visibility)) }
    }

    override fun onClickContinue() {
        val validationResult = validateFirstContentInput()
        if (validationResult) {
            _state.update {
                it.copy(
                    errors = it.errors.copy(
                        emailError = "",
                        passwordError = "",
                        confirmPasswordError = "",
                        usernameError = ""
                    )
                )
            }
            _state.update { it.copy(isFirstContent = false) }
        }
    }

    override fun onClickGender(gender: Gender) {
        _state.update { it.copy(signUpForm = it.signUpForm.copy(selectedGender = gender)) }
    }

    override fun onClickCountry(country: String) {
        val selectedCountry = state.value.signUpForm.countries.find { it.name == country }!!
        tryToExecute(
            { locationUseCase.getCountryGovernorates(selectedCountry.id.toString()) },
            { governorates ->
                _state.update {
                    it.copy(
                        signUpForm = it.signUpForm.copy(
                            governorates = governorates
                        )
                    )
                }
            },
            ::onError
        )
        _state.update { it.copy(signUpForm = it.signUpForm.copy(selectedCountry = selectedCountry.name)) }
    }

    override fun onClickGovernorate(governorate: String) {
        _state.update { it.copy(signUpForm = it.signUpForm.copy(selectedGovernorate = governorate)) }
    }

    override fun onClickSignIn() {
        sendNewEffect(SignupUiEffect.NavigateUp)
    }

    private fun onSignInWithFacebookSuccess(result: Result<UserAuth>) {
        if (result.isSuccess) {
            val userAuth =
                result.getOrDefault(UserAuth("", false, AuthServiceProvider.FACEBOOK))
            tryToExecute(
                { manageUser.saveToken(result.getOrDefault(userAuth).token) },
                ::onSaveTokenFromSignInWithFacebookSuccess,
                ::onError
            )
        } else {
            sendNewEffect(SignupUiEffect.ShowRequestFailed)
        }
    }

    private fun onSaveTokenFromSignInWithFacebookSuccess(unit: Unit) {
        tryToExecute(
            { manageUser.saveIfEmailIsActive(true) },
            { },
            ::onError
        )
        tryToExecute(
            { manageUser.saveAuthProvider(AuthServiceProvider.FACEBOOK.provider) },
            {
                _state.update { it.copy(isLoading = false) }
                sendNewEffect(SignupUiEffect.NavigateToHome)
            },
            ::onError
        )
    }

}