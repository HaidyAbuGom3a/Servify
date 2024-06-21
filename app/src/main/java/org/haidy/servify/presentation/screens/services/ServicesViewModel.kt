package org.haidy.servify.presentation.screens.services

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import org.haidy.servify.domain.usecase.ServicesUseCase
import org.haidy.servify.presentation.base.BaseViewModel
import org.haidy.servify.presentation.screens.home.toServiceUiState
import javax.inject.Inject

@HiltViewModel
class ServicesViewModel @Inject constructor(
    private val servicesUseCase: ServicesUseCase
) : BaseViewModel<ServicesUiState,
        ServicesUiEffect>(ServicesUiState()), ServicesInteractionListener {

    init {
        getServices()
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

    override fun onClickService(serviceName: String) {
        sendNewEffect(ServicesUiEffect.NavigateToServiceDetail(serviceName))
    }

    override fun onClickBackIcon() {
        sendNewEffect(ServicesUiEffect.NavigateUp)
    }

}