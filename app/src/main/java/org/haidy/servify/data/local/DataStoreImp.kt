package org.haidy.servify.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class DataStoreImp @Inject constructor(context: Context) : IDataStore {

    companion object {
        private const val PREFERENCES_FILE_NAME = "servify"
        private val KEY_TOKEN = stringPreferencesKey("token")
        private val IS_ACTIVE = booleanPreferencesKey("is_active")
        private val IS_FIRST_TIME = booleanPreferencesKey("is_first_time")
        private val LANGUAGE_CODE = stringPreferencesKey("language_code")
        private val THEME_MODE = stringPreferencesKey("theme_mode")
        private val EMAIL = stringPreferencesKey("email")
        private val PASSWORD = stringPreferencesKey("password")
        private val PROVIDER = stringPreferencesKey("provider")
        private val USER_ID = stringPreferencesKey("user_id")
    }

    private val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore(
        PREFERENCES_FILE_NAME
    )
    private val prefDataStore = context.preferencesDataStore

    override suspend fun saveToken(token: String) {
        prefDataStore.edit { preferences ->
            preferences[KEY_TOKEN] = token
        }
    }

    override suspend fun saveIfEmailIsActive(isActive: Boolean) {
        prefDataStore.edit { preferences ->
            preferences[IS_ACTIVE] = isActive
        }
    }

    override suspend fun saveIfFirstTimeUseApp(isFirstTime: Boolean) {
        prefDataStore.edit { preferences ->
            preferences[IS_FIRST_TIME] = isFirstTime
        }
    }

    override suspend fun saveLanguageCode(languageCode: String) {
        prefDataStore.edit { preferences ->
            preferences[LANGUAGE_CODE] = languageCode
        }
    }

    override suspend fun saveThemeMode(themeMode: String) {
        prefDataStore.edit { preferences ->
            preferences[THEME_MODE] = themeMode
        }
    }

    override fun getToken(): String {
        return runBlocking {
            prefDataStore.data.map { preferences -> preferences[KEY_TOKEN] }.first() ?: ""
        }
    }

    override suspend fun getIfFirstTimeUseApp(): Boolean {
        return prefDataStore.data.map { preferences -> preferences[IS_FIRST_TIME] }.first() ?: true
    }

    override suspend fun getIfEmailIsActive(): Boolean {
        return prefDataStore.data.map { preferences -> preferences[IS_ACTIVE] }.first() ?: false
    }

    override fun getLanguageCode(): String {
        return runBlocking {
            prefDataStore.data.map { preferences -> preferences[LANGUAGE_CODE] }.first() ?: "ar"
        }
    }

    override suspend fun getThemeMode(): String {
        return prefDataStore.data.map { preferences -> preferences[THEME_MODE] }.first() ?: "system"
    }

    override suspend fun saveEmail(email: String) {
        prefDataStore.edit { preferences ->
            preferences[EMAIL] = email
        }
    }

    override suspend fun getEmail(): String {
        return prefDataStore.data.map { preferences -> preferences[EMAIL] }.first() ?: ""
    }

    override suspend fun savePassword(password: String) {
        prefDataStore.edit { preferences ->
            preferences[PASSWORD] = password
        }
    }

    override suspend fun getPassword(): String {
        return prefDataStore.data.map { preferences -> preferences[PASSWORD] }.first() ?: ""
    }

    override suspend fun saveProvider(provider: String) {
        prefDataStore.edit { preferences ->
            preferences[PROVIDER] = provider
        }
    }

    override suspend fun getProvider(): String {
        return prefDataStore.data.map { preferences -> preferences[PROVIDER] }.first() ?: ""
    }

    override suspend fun saveUserId(userId: String) {
        prefDataStore.edit { preferences ->
            preferences[USER_ID] = userId
        }
    }

    override suspend fun getUserId(): String {
        return prefDataStore.data.map { preferences -> preferences[USER_ID] }.first() ?: ""
    }

}