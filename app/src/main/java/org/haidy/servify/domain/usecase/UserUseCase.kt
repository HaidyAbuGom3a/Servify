package org.haidy.servify.domain.usecase

import android.graphics.Bitmap
import org.haidy.servify.app.utils.LanguageCode
import org.haidy.servify.app.utils.ThemeMode
import org.haidy.servify.domain.model.User
import org.haidy.servify.domain.model.UserData
import org.haidy.servify.domain.repository.IUserRepository
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val userRepo: IUserRepository,
) {
    suspend fun saveToken(token: String) {
        userRepo.saveToken(token)
    }

    suspend fun saveIfEmailIsActive(isActive: Boolean) {
        userRepo.saveIfEmailVerified(isActive)
    }

    suspend fun saveIfFirstTimeUseApp(isFirstTime: Boolean) {
        userRepo.saveIfFirstTimeUseApp(isFirstTime)
    }

    suspend fun saveLanguageCode(languageCode: String) {
        userRepo.saveLanguageCode(languageCode)
    }

    suspend fun getIfFirstTimeUseApp(): Boolean {
        return userRepo.getIfFirstTimeUseApp()
    }

    suspend fun getIfUserIsLoggedIn(): Boolean {
        return getToken().isNotEmpty() && getIfEmailIsActive()
    }

    private suspend fun getIfEmailIsActive(): Boolean {
        return userRepo.getIfEmailVerified()
    }

    private suspend fun getToken(): String {
        return userRepo.getToken()
    }

    fun getLanguageCode(): LanguageCode {
        return userRepo.getLanguageCode()
    }

    suspend fun saveThemeMode(themeMode: String) {
        userRepo.saveThemeModeCode(themeMode)
    }

    suspend fun getThemeMode(): ThemeMode {
        return userRepo.getThemeMode()
    }

    suspend fun getUserProfile(): User {
        return userRepo.getUserProfile()
    }

    suspend fun updateImage(imageBitmap: Bitmap): Boolean {
        return userRepo.updateImage(imageBitmap)
    }

    suspend fun saveUserId(userId: String) {
        return userRepo.saveUserId(userId)
    }

    suspend fun getUserId(): String{
        return userRepo.getUserId()
    }

    suspend fun saveEmail(email: String) {
        userRepo.saveEmail(email)
    }

    suspend fun savePassword(password: String) {
        userRepo.savePassword(password)
    }

    suspend fun saveAuthProvider(provider: String) {
        userRepo.saveProvider(provider)
    }

    suspend fun getUserData(userId: String): UserData {
        return userRepo.getUserData(userId)
    }

}