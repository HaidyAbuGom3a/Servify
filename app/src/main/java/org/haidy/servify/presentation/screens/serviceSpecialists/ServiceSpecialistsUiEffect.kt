package org.haidy.servify.presentation.screens.serviceSpecialists

sealed class ServiceSpecialistsUiEffect {
    data class NavigateToBookAppointment(val specId: String) : ServiceSpecialistsUiEffect()
    data class NavigateToChat(val specId: String) : ServiceSpecialistsUiEffect()
    object NavigateUp : ServiceSpecialistsUiEffect()
}