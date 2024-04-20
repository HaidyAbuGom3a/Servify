package org.haidy.servify.domain.model

data class FormValidation(
    val isSuccessful: Boolean,
    val message: String = ""
)