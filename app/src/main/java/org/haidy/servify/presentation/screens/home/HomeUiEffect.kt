package org.haidy.servify.presentation.screens.home

sealed class HomeUiEffect {
    object NavigateToProfile : HomeUiEffect()
    object NavigateToSettings : HomeUiEffect()
    object NavigateToSearch: HomeUiEffect()
    object NavigateToNotifications : HomeUiEffect()
    object NavigateToLogin : HomeUiEffect()
    object NavigateToServices : HomeUiEffect()
    object NavigateToBestSpecialists : HomeUiEffect()
    object NavigateToAddService : HomeUiEffect()
    object NavigateToAddCard : HomeUiEffect()
    data class NavigateToBookingAppointment(val specialistId: String): HomeUiEffect()
    data class NavigateToServiceSpecialists(val serviceName: String): HomeUiEffect()
}
