package org.haidy.servify.domain.model

import java.util.Date

data class User(
    val username: String,
    val email: String,
    val phoneNumber: String,
    val imageUrl: String,
    val type: Type?,
    val gender: Gender?,
    val birthday: Date?,
    val location: Location
)


enum class Gender {
    MALE,
    FEMALE
}

enum class Type {
    USER,
    SPECIALIST
}