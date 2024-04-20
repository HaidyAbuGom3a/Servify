package org.haidy.servify.app.utils

enum class LanguageCode(val code: String) {
    AR("ar"),
    EG("eg"),
    EN("en");

    companion object {
        fun getLanguageCode(value: String): LanguageCode {
            return values().find { it.code == value } ?: EG
        }
    }

}