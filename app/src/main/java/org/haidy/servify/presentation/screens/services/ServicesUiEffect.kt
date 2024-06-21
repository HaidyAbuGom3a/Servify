package org.haidy.servify.presentation.screens.services

sealed class ServicesUiEffect {
    data class NavigateToServiceDetail(val serviceName: String) : ServicesUiEffect()
    object NavigateUp : ServicesUiEffect()
}