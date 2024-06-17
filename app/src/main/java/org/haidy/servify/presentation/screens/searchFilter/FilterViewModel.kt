package org.haidy.servify.presentation.screens.searchFilter

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import org.haidy.servify.domain.usecase.ServicesUseCase
import org.haidy.servify.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(private val servicesUseCase: ServicesUseCase) :
    BaseViewModel<FilterUiState, FilterUiEffect>(FilterUiState()), FilterInteractionListener {
    override fun onClickBackIcon() {
        sendNewEffect(FilterUiEffect.NavigateUp)
    }

    init {
        getServices()
    }

    private fun getServices() {
        tryToExecute(
            {
                servicesUseCase.getAllServices()
            },
            { services ->
                _state.update { it.copy(services = services) }
            },
            { }
        )
    }

    override fun onClickService(serviceName: String) {
        _state.update { it.copy(selectedService = serviceName) }
    }

    override fun onClickRating(rating: String) {
        _state.update { it.copy(selectedRating = rating) }
    }

    override fun onNameChanged(name: String) {
        _state.update { it.copy(specialistName = name) }
    }

    override fun onClickApplyFilter() {
        sendNewEffect(FilterUiEffect.NavigateUp)
    }

    override fun onClickResetFilter() {
        _state.update { FilterUiState() }
        sendNewEffect(FilterUiEffect.NavigateUp)
    }
}