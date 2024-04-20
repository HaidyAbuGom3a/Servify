package org.haidy.servify.data.repository

import android.content.Context
import android.graphics.Bitmap
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.haidy.servify.app.utils.LanguageCode
import org.haidy.servify.app.utils.ThemeMode
import org.haidy.servify.data.local.IDataStore
import org.haidy.servify.data.mapper.toUser
import org.haidy.servify.data.remote.network.ServifyApiService
import org.haidy.servify.data.util.convertToFile
import org.haidy.servify.domain.model.Location
import org.haidy.servify.domain.model.User
import org.haidy.servify.domain.repository.IUserRepository
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val apiService: ServifyApiService,
    private val dataStore: IDataStore,
    private val context: Context
) :
    IUserRepository, BaseRepository() {
    override suspend fun saveIfFirstTimeUseApp(value: Boolean) {
        dataStore.saveIfFirstTimeUseApp(value)
    }

    override suspend fun getIfFirstTimeUseApp(): Boolean {
        return dataStore.getIfFirstTimeUseApp()
    }

    override suspend fun saveToken(token: String) {
        dataStore.saveToken(token)
    }

    override suspend fun getToken(): String {
        return dataStore.getToken()
    }

    override suspend fun saveIfEmailVerified(isActive: Boolean) {
        dataStore.saveIfEmailIsActive(isActive)
    }

    override suspend fun getIfEmailVerified(): Boolean {
        return dataStore.getIfEmailIsActive()
    }

    override suspend fun saveLanguageCode(languageCode: String) {
        dataStore.saveLanguageCode(languageCode)
    }

    override fun getLanguageCode(): LanguageCode {
        val languageCode = dataStore.getLanguageCode()
        return LanguageCode.getLanguageCode(languageCode)
    }

    override suspend fun saveThemeModeCode(themeMode: String) {
        return dataStore.saveThemeMode(themeMode)
    }

    override suspend fun getThemeMode(): ThemeMode {
        val themeCode = dataStore.getThemeMode()
        return ThemeMode.getThemeMode(themeCode)
    }

    override suspend fun saveEmail(email: String) {
        dataStore.saveEmail(email)
    }

    override suspend fun getEmail(): String {
        return dataStore.getEmail()
    }

    override suspend fun savePassword(password: String) {
        dataStore.savePassword(password)
    }

    override suspend fun getPassword(): String {
        return dataStore.getPassword()
    }

    override suspend fun saveProvider(provider: String) {
        dataStore.saveProvider(provider)
    }

    override suspend fun getProvider(): String {
        return dataStore.getProvider()
    }

    override suspend fun saveUserId(userId: String) {
        dataStore.saveUserId(userId)
    }

    override suspend fun getUserId(): String {
        return dataStore.getUserId()
    }

    override suspend fun updateImage(imageBitmap: Bitmap): Boolean {
        val multipartImage = imageBitmap.let { bitmap ->
            val file = convertToFile(bitmap, context)
            MultipartBody.Part.createFormData(
                "image",
                file.name,
                file.asRequestBody("image/*".toMediaTypeOrNull())
            )
        }
        return wrapResponse { apiService.updateImage(multipartImage) }?.status ?: false
    }

    override suspend fun getUserProfile(): User {
        return wrapResponse { apiService.getUserProfile() }?.data?.toUser() ?: User(
            "", "", "", "", null, null, null, Location("", "", 0.0, 0.0)
        )
    }
}