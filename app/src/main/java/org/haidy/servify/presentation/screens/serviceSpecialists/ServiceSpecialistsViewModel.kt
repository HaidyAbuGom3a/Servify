package org.haidy.servify.presentation.screens.serviceSpecialists

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import org.haidy.servify.domain.usecase.SpecialistsUseCase
import org.haidy.servify.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ServiceSpecialistsViewModel @Inject constructor(
    private val specialistsUseCase: SpecialistsUseCase,
    savedStateHandle: SavedStateHandle
) :
    BaseViewModel<ServiceSpecialistsUiState, ServiceSpecialistsUiEffect>(ServiceSpecialistsUiState()),
    ServiceSpecialistsInteractionListener {

    private val args: ServiceSpecialistsArgs = ServiceSpecialistsArgs(savedStateHandle)

    init {
        _state.update { it.copy(serviceName = args.serviceName) }
        getSpecialists()
    }

    private fun getSpecialists() {
        tryToExecute(
            { specialistsUseCase.getServiceSpecialists(args.serviceName) },
            { specialists -> _state.update { it.copy(specialists = specialists) } },
            {}
        )
    }

    override fun onClickBookNow(specId: String) {
        sendNewEffect(ServiceSpecialistsUiEffect.NavigateToBookAppointment(specId))
    }

    override fun onClickBackIcon() {
        sendNewEffect(ServiceSpecialistsUiEffect.NavigateUp)
    }

    override fun onClickChat(specId: String) {
        sendNewEffect(ServiceSpecialistsUiEffect.NavigateToChat(specId))
    }
}