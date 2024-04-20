package org.haidy.servify.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    @SerializedName("status") val status: Boolean? = null,
    @SerializedName("msg") val message: String? = null,
    @SerializedName("error") val errors: List<String>? = null,
    @SerializedName("data") val data: T? = null
)
