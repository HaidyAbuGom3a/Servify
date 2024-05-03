package org.haidy.servify.presentation.screens.services

sealed class ServicesUiEffect {
    data class NavigateToServiceDetail(val serviceId: String) : ServicesUiEffect()
    object NavigateUp : ServicesUiEffect()
}