package org.haidy.servify.domain.model

data class UserAuth(
    val token: String,
    val isEmailVerified: Boolean,
    val provider: AuthServiceProvider,
)

enum class AuthServiceProvider(val provider: String) {
    GOOGLE("google"),
    FACEBOOK("facebook"),
    EMAIL("email");

    companion object {
        fun getProvider(provider: String): AuthServiceProvider {
            return values().find { it.provider ==  provider.lowercase() } ?: EMAIL
        }
    }
}