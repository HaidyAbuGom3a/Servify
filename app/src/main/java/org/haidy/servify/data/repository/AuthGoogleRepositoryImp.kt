package org.haidy.servify.data.repository

import android.content.Intent
import android.content.IntentSender
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import org.haidy.servify.data.mapper.toUserAuth
import org.haidy.servify.data.remote.network.ServifyApiService
import org.haidy.servify.domain.model.AuthServiceProvider
import org.haidy.servify.domain.model.UserAuth
import org.haidy.servify.domain.repository.IAuthGoogleRepository
import org.haidy.servify.domain.repository.IUserRepository
import java.util.concurrent.CancellationException
import javax.inject.Inject

class AuthGoogleRepositoryImp @Inject constructor(
    private val apiService: ServifyApiService,
    private val webClientId: String,
    private val oneTapClient: SignInClient,
    private val auth: FirebaseAuth,
    private val userRepository: IUserRepository,
) : IAuthGoogleRepository, BaseRepository() {

    override suspend fun signIn(): IntentSender? {
        val result = try {
            oneTapClient.beginSignIn(
                buildSignInRequest()
            ).await()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            throw e
        }
        return result?.pendingIntent?.intentSender
    }

    override suspend fun signUpGoogleWithIntent(intent: Intent): Result<UserAuth> {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
        return try {
            val user = auth.signInWithCredential(googleCredentials).await().user
            val userAuth =
                wrapResponse { apiService.loginWithGoogle(user?.uid ?: "") }
                    ?.data?.userAuth?.toUserAuth() ?: UserAuth(
                    "",
                    false,
                    AuthServiceProvider.GOOGLE
                )
            Result.success(userAuth)
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Result.failure<Exception>(e)
            throw e
        }
    }

    override suspend fun loginGoogleWithIntent(intent: Intent): Result<UserAuth> {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
        return try {
            val user = auth.signInWithCredential(googleCredentials).await().user
            userRepository.saveUserId(user?.uid ?: "")
            val response = loginWithId(user?.uid ?: "")
            Result.success(response)
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Result.failure<Exception>(e)
            throw e
        }
    }

    override suspend fun loginWithId(id: String): UserAuth {
        val response = wrapResponse { apiService.loginWithGoogle(id) }?.data?.userAuth?.toUserAuth()
        return response ?: UserAuth("", false, AuthServiceProvider.GOOGLE)
    }


    override suspend fun checkIfUserIsLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    override suspend fun signOut() {
        try {
            oneTapClient.signOut().await()
            auth.signOut()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            throw e
        }
    }

    private fun buildSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(webClientId)
                    .build()
            ).setAutoSelectEnabled(true)
            .build()
    }
}