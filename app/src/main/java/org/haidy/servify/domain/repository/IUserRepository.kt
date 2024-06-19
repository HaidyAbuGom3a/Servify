package org.haidy.servify.domain.repository

import android.graphics.Bitmap
import org.haidy.servify.app.utils.LanguageCode
import org.haidy.servify.app.utils.ThemeMode
import org.haidy.servify.domain.model.User
import org.haidy.servify.domain.model.UserData

interface IUserRepository {
    suspend fun saveIfFirstTimeUseApp(value: Boolean)
    suspend fun getIfFirstTimeUseApp(): Boolean
    suspend fun saveToken(token: String)
    suspend fun getToken(): String
    suspend fun saveIfEmailVerified(isActive: Boolean)
    suspend fun getIfEmailVerified(): Boolean
    suspend fun saveLanguageCode(languageCode: String)
    fun getLanguageCode(): LanguageCode
    suspend fun saveThemeModeCode(themeMode: String)
    suspend fun getThemeMode(): ThemeMode
    suspend fun saveEmail(email: String)
    suspend fun getEmail(): String
    suspend fun savePassword(password: String)
    suspend fun getPassword(): String
    suspend fun saveProvider(provider: String)
    suspend fun getProvider(): String
    suspend fun saveUserId(userId: String)
    suspend fun getUserId(): String
    suspend fun updateImage(imageBitmap: Bitmap): Boolean
    suspend fun getUserProfile(): User
    suspend fun getUserData(userId: String): UserData
}