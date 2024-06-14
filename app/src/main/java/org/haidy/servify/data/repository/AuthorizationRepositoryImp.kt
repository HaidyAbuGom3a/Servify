package org.haidy.servify.data.repository

import android.content.Context
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.haidy.servify.data.mapper.toUserAuth
import org.haidy.servify.data.remote.network.ServifyApiService
import org.haidy.servify.data.util.convertToFile
import org.haidy.servify.data.util.toStringOrEmpty
import org.haidy.servify.domain.model.AuthServiceProvider
import org.haidy.servify.domain.model.FormSignUp
import org.haidy.servify.domain.model.UserAuth
import org.haidy.servify.domain.repository.IAuthorizationRepository
import javax.inject.Inject

class AuthorizationRepositoryImp @Inject constructor(
    private val apiService: ServifyApiService,
    private val context: Context
) :
    IAuthorizationRepository, BaseRepository() {

    override suspend fun signupUser(signUpForm: FormSignUp): Boolean {
        val multipartImage = signUpForm.imageBitmap?.let { bitmap ->
            val file = convertToFile(bitmap, context)
            MultipartBody.Part.createFormData(
                "image",
                file.name,
                file.asRequestBody("image/*".toMediaTypeOrNull())
            )
        }

        return wrapResponse {
            apiService.signupUser(
                username = signUpForm.username.toRequestBody(),
                email = signUpForm.email.toRequestBody(),
                password = signUpForm.password.toRequestBody(),
                passwordConfirmation = signUpForm.passwordConfirmation.toRequestBody(),
                phone= signUpForm.phoneNumber?.toRequestBody() ?: "".toRequestBody(),
                image = multipartImage,
                countryId = signUpForm.countryId.toStringOrEmpty().toRequestBody(),
                governorateId = signUpForm.governorateId.toStringOrEmpty().toRequestBody(),
                gender = signUpForm.gender?.name?.lowercase()?.toRequestBody() ?: "".toRequestBody(),
                latitude = signUpForm.latitude.toStringOrEmpty().toRequestBody(),
                longitude = signUpForm.longitude.toStringOrEmpty().toRequestBody()
            )
        }?.status ?: false
    }

    override suspend fun sendVerificationCode(email: String): Boolean {
        return wrapResponse {
            apiService.sendVerificationCode(email)
        }?.message != null
    }

    override suspend fun loginUser(email: String, password: String): UserAuth {
        return wrapResponse { apiService.loginUser(email, password) }?.data?.userAuth?.toUserAuth()
            ?: UserAuth("", false, AuthServiceProvider.EMAIL)
    }

    override suspend fun sendOtpToEmail(email: String): Boolean {
        val response = wrapResponse { apiService.sendOtpToEmail(email) }
        return response?.message != null
    }

    override suspend fun confirmOtp(otp: String): Boolean {
        return wrapResponse { apiService.confirmOtp(otp) }?.status ?: false
    }

    override suspend fun verifyEmail(otp: String): Boolean {
        return wrapResponse { apiService.verifyEmail(otp) }?.status ?: false
    }

    override suspend fun changePassword(otp: String, newPassword: String): Boolean {
        val response = wrapResponse {
            apiService.resetPassword(
                otp, newPassword, newPassword
            )
        }
        return response?.message != "OTP is not correct" && response?.message != null
    }

}