package org.haidy.servify.domain.repository

import org.haidy.servify.domain.model.UserAuth

interface IAuthFacebookRepository {

    suspend fun signIn(): Result<UserAuth>

    suspend fun loginWithId(id: String): UserAuth

    suspend fun signOut()


}