package org.haidy.servify.data.mapper

import org.haidy.servify.data.dto.UserAuthDto
import org.haidy.servify.domain.model.AuthServiceProvider
import org.haidy.servify.domain.model.UserAuth

fun UserAuthDto.toUserAuth(): UserAuth {
    return UserAuth(
        token = token ?: "",
        isEmailVerified = emailActive?.lowercase() == "yes",
        provider = AuthServiceProvider.getProvider(provider ?: "")
    )
}