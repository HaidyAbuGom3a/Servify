package org.haidy.servify.data.mapper

import org.haidy.servify.data.dto.UserDataDto
import org.haidy.servify.domain.model.UserData

fun UserDataDto?.toUserData(): UserData {
    if(this == null) return UserData(
        "",
        "",
        "",
        ""
    )
    return UserData(
        userId = id ?: "",
        name = name ?: "",
        userImageUrl = image ?: "",
        email = email ?: ""
    )
}