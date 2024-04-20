package org.haidy.servify.data.remote.network

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.data.local.IDataStore
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val dataStore: IDataStore,
    private val refreshToken: RefreshToken
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = dataStore.getToken()
        val request = chain
            .request()
            .newBuilder()
            .addHeader(AUTHORIZATION, token)
            .addHeader(LANGUAGE, Resources.languageCode.value.code)
            .build()

        val response = chain.proceed(request)
        println("haidy found response code ${response.code}, token $token")
        return if (response.code == UN_AUTHORIZED && token.isNotEmpty()) {
            response.close()
            runBlocking {
                refreshToken()
                val newToken = dataStore.getToken()
                tryRequestAgain(chain, newToken)
            }
        } else {
            response
        }
    }

    private fun tryRequestAgain(chain: Interceptor.Chain, token: String): Response {
        val newRequest = chain
            .request()
            .newBuilder()
            .addHeader(AUTHORIZATION, token)
            .addHeader(LANGUAGE, Resources.languageCode.value.code)
            .build()
        return chain.proceed(newRequest)
    }

    companion object {
        const val AUTHORIZATION = "Authorization"
        const val LANGUAGE = "lang"
        const val GOOGLE = "google"
        const val FACEBOOK = "facebook"
        const val UN_AUTHORIZED = 401
    }
}
