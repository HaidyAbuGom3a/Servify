package org.haidy.servify.domain.model

data class Service(
    val id: String,
    val description: String,
    val status: Boolean,
    val name: String,
    val imageUrl: String
)
