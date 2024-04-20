package org.haidy.servify.domain.repository

import org.haidy.servify.domain.model.FormSignUp
import org.haidy.servify.domain.model.UserAuth

interface IAuthorizationRepository {

    suspend fun signupUser(signUpForm: FormSignUp): Boolean

    suspend fun loginUser(email: String, password: String): UserAuth

    suspend fun sendVerificationCode(email: String): Boolean

    suspend fun sendOtpToEmail(email: String): Boolean

    suspend fun confirmOtp(otp: String): Boolean

    suspend fun verifyEmail(otp: String): Boolean

    suspend fun changePassword(otp: String, newPassword: String): Boolean

}