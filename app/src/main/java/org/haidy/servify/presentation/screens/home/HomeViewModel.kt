package org.haidy.servify.presentation.screens.home

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import org.haidy.servify.domain.usecase.AuthUseCase
import org.haidy.servify.domain.usecase.ServicesUseCase
import org.haidy.servify.domain.usecase.SpecialistsUseCase
import org.haidy.servify.domain.usecase.UserUseCase
import org.haidy.servify.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val authUseCase: AuthUseCase,
    private val servicesUseCase: ServicesUseCase,
    private val specialistsUseCase: SpecialistsUseCase
) : BaseViewModel<HomeUiState, HomeUiEffect>(HomeUiState()), HomeInteractionListener {

    init {
        getUser()
        getServices()
        getOffers()
        getBestSpecialists()
    }

    private fun getBestSpecialists() {
        tryToExecute(
            { specialistsUseCase.getBestSpecialists() },
            { specialists -> _state.update { it.copy(bestSpecialists = specialists) } },
            {}
        )
    }

    private fun getServices() {
        tryToExecute(
            {
                _state.update { it.copy(isLoading = true) }
                servicesUseCase.getAllServices()
            },
            { services ->
                _state.update { it.copy(services = services.toServiceUiState(), isLoading = false) }
            },
            { _state.update { it.copy(isLoading = false) } }
        )
    }

    private fun getOffers() {
        tryToExecute(
            {
                _state.update { it.copy(isLoading = true) }
                servicesUseCase.getAllOffers()
            },
            { offers ->
                _state.update { it.copy(offers = offers, isLoading = false) }
            },
            { _state.update { it.copy(isLoading = false) } }
        )
    }

    private fun getUser() {
        tryToExecute(
            {
                _state.update { it.copy(isLoading = true) }
                userUseCase.getUserProfile()
            },
            { user ->
                _state.update {
                    it.copy(
                        userName = user.username,
                        userImageUrl = user.imageUrl,
                        isLoading = false
                    )
                }
            },
            { _state.update { it.copy(isLoading = false) } }
        )
    }

    fun refreshData() {
        getUser()
        getServices()
    }

    override fun onClickProfile() {
        sendNewEffect(HomeUiEffect.NavigateToProfile)
    }

    override fun onClickSettings() {
        sendNewEffect(HomeUiEffect.NavigateToSettings)
    }

    override fun onClickNotifications() {
        sendNewEffect(HomeUiEffect.NavigateToNotifications)
    }

    override fun onClickLogout() {
        tryToExecute(
            {
                _state.update { it.copy(isLoading = true) }
                authUseCase.logout()
            },
            {
                _state.update { it.copy(isLoading = false) }
                sendNewEffect(HomeUiEffect.NavigateToLogin)
            },
            {
                _state.update { it.copy(isLoading = false) }
            }
        )
    }

    override fun onClickShowAllServices() {
        sendNewEffect(HomeUiEffect.NavigateToServices)
    }

    override fun onClickShowAllSpecialists() {
        sendNewEffect(HomeUiEffect.NavigateToBestSpecialists)
    }

    override fun onClickAddService() {
        sendNewEffect(HomeUiEffect.NavigateToAddService)
    }

    override fun onClickAddCard() {
        sendNewEffect(HomeUiEffect.NavigateToAddCard)
    }

    override fun onClickBookNow(specialistId: String) {
        sendNewEffect(HomeUiEffect.NavigateToBookingAppointment(specialistId))
    }

    override fun onClickSearch() {
        sendNewEffect(HomeUiEffect.NavigateToSearch)
    }
}