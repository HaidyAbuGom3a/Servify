package org.haidy.servify.presentation.screens.specialists

sealed class SpecialistsUiEffect{
    data class NavigateToBookAppointment(val specId: String): SpecialistsUiEffect()
    object NavigateUp: SpecialistsUiEffect()
}