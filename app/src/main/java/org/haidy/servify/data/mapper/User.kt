package org.haidy.servify.data.mapper

import org.haidy.servify.data.dto.UserDto
import org.haidy.servify.data.dto.UserProfileDto
import org.haidy.servify.domain.model.Gender
import org.haidy.servify.domain.model.Location
import org.haidy.servify.domain.model.Type
import org.haidy.servify.domain.model.User

fun UserDto.toUser(): User {
    return User(
        username = name ?: "",
        email = email ?: "",
        phoneNumber = phone ?: "",
        imageUrl = image ?: "",
        type = if (role == "user") Type.USER else Type.SPECIALIST,
        location = userLocation?.toLocation() ?: Location("", "", 0.0, 0.0),
        birthday = null,
        gender = if (gender == "male") Gender.MALE else if (gender == "female") Gender.FEMALE else null
    )
}

fun UserProfileDto.toUser(): User {
    val image = if (image == null || image == "Not Found") "" else image
    return User(
        username = name ?: "",
        email = email ?: "",
        phoneNumber = phone ?: "",
        imageUrl = image,
        location = Location(country ?: "", governorate ?: "", 0.0, 0.0),
        birthday = null,
        gender = if (gender == "male" || gender == "ذكر") Gender.MALE else if (gender == "female" || gender == "أنثى") Gender.FEMALE else null,
        type = null
    )
}