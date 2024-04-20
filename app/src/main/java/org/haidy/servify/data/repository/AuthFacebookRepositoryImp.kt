package org.haidy.servify.data.repository

import com.facebook.AccessToken
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import org.haidy.servify.app.utils.CallbackManagerProvider
import org.haidy.servify.data.mapper.toUserAuth
import org.haidy.servify.data.remote.network.ServifyApiService
import org.haidy.servify.domain.model.AuthServiceProvider
import org.haidy.servify.domain.model.UserAuth
import org.haidy.servify.domain.repository.IAuthFacebookRepository
import org.haidy.servify.domain.repository.IUserRepository
import java.util.concurrent.CancellationException
import javax.inject.Inject


class AuthFacebookRepositoryImp @Inject constructor(
    private val apiService: ServifyApiService,
    private val auth: FirebaseAuth,
    private val userRepository: IUserRepository,
) :
    IAuthFacebookRepository, BaseRepository() {

    override suspend fun signIn(): Result<UserAuth> {
        return try {
            val resultDeferred = CompletableDeferred<Result<UserAuth>>()
            LoginManager.getInstance()
                .logInWithReadPermissions(
                    CallbackManagerProvider.activity,
                    listOf("public_profile", "email")
                )
            LoginManager.getInstance()
                .registerCallback(
                    CallbackManagerProvider.callbackManager,
                    getCallback { loginResult ->
                        resultDeferred.complete(onSuccess(loginResult))
                    }
                )
            resultDeferred.await()
        } catch (e: Exception) {
            if (e is CancellationException) Result.failure<Exception>(e)
            Result.failure(e)
        }
    }

    override suspend fun loginWithId(id: String): UserAuth {
        return wrapResponse { apiService.loginWithFacebook(id) }?.data?.userAuth?.toUserAuth()
            ?: UserAuth("", false, AuthServiceProvider.FACEBOOK)
    }


    override suspend fun signOut() {
        LoginManager.getInstance().logOut()
        auth.signOut()
    }

    private fun getCallback(onSuccess: suspend (LoginResult) -> Unit): FacebookCallback<LoginResult> {
        return object : FacebookCallback<LoginResult> {
            override fun onCancel() {
                println("Request is Cancelled")
            }

            override fun onError(error: FacebookException) {
                throw error
            }

            override fun onSuccess(result: LoginResult) {
                CoroutineScope(Dispatchers.IO).launch {
                    onSuccess(result)
                }
            }
        }

    }

    private suspend fun onSuccess(result: LoginResult): Result<UserAuth> {
        return loginFacebook(result.accessToken)
    }


    private suspend fun loginFacebook(token: AccessToken): Result<UserAuth> = coroutineScope {
        try {
            val credential = FacebookAuthProvider.getCredential(token.token)
            val user = auth.signInWithCredential(credential).await().user
            userRepository.saveUserId(user?.uid ?: "")
            val response = loginWithId(user?.uid ?: "")
            Result.success(response)

        } catch (e: Exception) {
            if (e is CancellationException) throw e
            println("Facebook login failure $e")
            Result.failure(e)
        }
    }

}