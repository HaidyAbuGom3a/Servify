package org.haidy.servify.data.remote.network

import android.util.Log
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.data.dto.BaseResponse
import org.haidy.servify.data.dto.UserDto
import org.haidy.servify.data.local.IDataStore
import javax.inject.Inject

class RefreshToken @Inject constructor(
    private val dataStore: IDataStore,
) {
    suspend operator fun invoke() {
        val provider = dataStore.getProvider()
        val userId = dataStore.getUserId()

        if (provider == AuthInterceptor.GOOGLE) {
            val response = loginWithGoogle(userId)
            val token = getTokenFromResponse(response)
            dataStore.saveToken(token)
            return
        }
        if (provider == AuthInterceptor.FACEBOOK) {
            val response = loginWithFacebook(userId)
            val token = getTokenFromResponse(response)
            dataStore.saveToken(token)
            return
        }
        val email = dataStore.getEmail()
        val password = dataStore.getPassword()
        val response = loginWithEmail(email, password)
        val token = getTokenFromResponse(response)
        print("refreshed token $token")
        dataStore.saveToken(token)
    }

    private fun loginWithEmail(email: String, password: String): Response {
        val client = OkHttpClient()
        val formBody = FormBody.Builder()
            .add("email", email)
            .add("password", password)
            .build()
        val newRequest = Request.Builder()
            .post(formBody)
            .header("Content-Type", "application/x-www-form-urlencoded")
            .url("${BASE_URL}/login")
            .build()
        return client.newCall(newRequest).execute()
    }

    private fun loginWithGoogle(userId: String): Response {
        val client = OkHttpClient()
        val newRequest = Request.Builder()
            .header("lang", Resources.languageCode.value.code)
            .url("${BASE_URL}/login/google/callback/$userId")
            .build()
        return client.newCall(newRequest).execute()
    }

    private fun loginWithFacebook(userId: String): Response {
        val client = OkHttpClient()
        val newRequest = Request.Builder()
            .header("lang", Resources.languageCode.value.code)
            .url("${BASE_URL}/login/facebook/callback/$userId")
            .build()
        return client.newCall(newRequest).execute()
    }

    private fun getTokenFromResponse(response: Response): String {
        val jsonData = response.body.string()
        val gson = Gson()
        try {
            val type = object : TypeToken<BaseResponse<UserDto>>() {}.type
            val tokensResponse: BaseResponse<UserDto> = gson.fromJson(jsonData, type)
            return tokensResponse.data?.userAuth?.token ?: ""
        } catch (e: Exception) {
            Log.e("refresh token", "error in get token from response ${e.message}")
        }
        return ""
    }


    companion object {
        const val BASE_URL = "http://54.196.133.53/api"
    }
}