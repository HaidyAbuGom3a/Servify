package org.haidy.servify.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserAuthDto(
    @SerializedName("provider")
    val provider: String? = null,
    @SerializedName("token")
    val token: String? = null,
    @SerializedName("email_active")
    val emailActive: String? = null,
    @SerialName("verification_code")
    val verificationCode: Int? = null,
    @SerialName("verification_code_created_at")
    val verificationCodeCreatedAt: String? = null,
    @SerialName("otp")
    val otp: Int? = null,
    @SerialName("remember_token")
    val rememberToken: String? = null,
)
