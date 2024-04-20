package org.haidy.servify.data.local

interface IDataStore {
    suspend fun saveToken(token: String)
    suspend fun saveIfEmailIsActive(isActive: Boolean)
    suspend fun saveIfFirstTimeUseApp(isFirstTime: Boolean)
    fun getToken(): String
    suspend fun getIfFirstTimeUseApp(): Boolean
    suspend fun getIfEmailIsActive(): Boolean
    suspend fun saveLanguageCode(languageCode: String)
    fun getLanguageCode(): String
    suspend fun saveThemeMode(themeMode:String)
    suspend fun getThemeMode(): String
    suspend fun saveEmail(email: String)
    suspend fun getEmail(): String
    suspend fun savePassword(password: String)
    suspend fun getPassword(): String
    suspend fun saveProvider(provider: String)
    suspend fun getProvider(): String
    suspend fun saveUserId(userId: String)
    suspend fun getUserId(): String
}