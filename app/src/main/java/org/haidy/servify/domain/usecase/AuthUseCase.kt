package org.haidy.servify.domain.usecase

import android.content.Intent
import android.content.IntentSender
import org.haidy.servify.domain.model.FormSignUp
import org.haidy.servify.domain.model.UserAuth
import org.haidy.servify.domain.repository.IAuthFacebookRepository
import org.haidy.servify.domain.repository.IAuthGoogleRepository
import org.haidy.servify.domain.repository.IAuthorizationRepository
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val authRepo: IAuthorizationRepository,
    private val googleAuthRepo: IAuthGoogleRepository,
    private val facebookRepository: IAuthFacebookRepository,
    private val userUseCase: UserUseCase
) {
    suspend fun signupUser(signUpForm: FormSignUp): Boolean {
        return authRepo.signupUser(signUpForm)
    }

    suspend fun loginUser(email: String, password: String): UserAuth {
        return authRepo.loginUser(email, password)
    }

    suspend fun sendOtpToEmail(email: String): Boolean {
        return authRepo.sendOtpToEmail(email)
    }

    suspend fun confirmOtp(otp: String): Boolean {
        return authRepo.confirmOtp(otp)
    }

    suspend fun changePassword(otp: String, newPassword: String): Boolean {
        return authRepo.changePassword(otp, newPassword)
    }

    suspend fun signInWithGoogle(): IntentSender? {
        return googleAuthRepo.signIn()
    }

    suspend fun sendVerificationCode(email: String): Boolean {
        return authRepo.sendVerificationCode(email)
    }

    suspend fun verifyEmail(otp: String): Boolean {
        return authRepo.verifyEmail(otp)
    }

    suspend fun googleSignUpWithIntent(intent: Intent): Result<UserAuth> {
        return googleAuthRepo.signUpGoogleWithIntent(intent)
    }

    suspend fun googleLoginWithIntent(intent: Intent): Result<UserAuth> {
        return googleAuthRepo.loginGoogleWithIntent(intent)
    }

    suspend fun signInWithFacebook(): Result<UserAuth> {
        return facebookRepository.signIn()
    }

    suspend fun logout() {
        googleAuthRepo.signOut()
        facebookRepository.signOut()
        userUseCase.saveToken("")
        userUseCase.saveIfEmailIsActive(false)
        userUseCase.saveEmail("")
        userUseCase.savePassword("")
        userUseCase.saveAuthProvider("")
        userUseCase.saveUserId("")
    }
}