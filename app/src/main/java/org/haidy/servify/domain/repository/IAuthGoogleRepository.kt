package org.haidy.servify.domain.repository

import android.content.Intent
import android.content.IntentSender
import org.haidy.servify.domain.model.User
import org.haidy.servify.domain.model.UserAuth

interface IAuthGoogleRepository {

    suspend fun signIn(): IntentSender?

    suspend fun signUpGoogleWithIntent(intent: Intent): Result<UserAuth>

    suspend fun loginGoogleWithIntent(intent: Intent): Result<UserAuth>

    suspend fun loginWithId(id: String): UserAuth

    suspend fun signOut()

    suspend fun checkIfUserIsLoggedIn(): Boolean

}