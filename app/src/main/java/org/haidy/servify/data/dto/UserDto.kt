package org.haidy.servify.data.dto


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class UserDto(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("phone")
    val phone: String? = null,
    @SerializedName("role")
    val role: String? = null,
    @SerializedName("gender")
    val gender: String? = null,
    @SerializedName("user_auth")
    val userAuth: UserAuthDto? = null,
    @SerializedName("user_location")
    val userLocation: UserLocationDto? = null,
)