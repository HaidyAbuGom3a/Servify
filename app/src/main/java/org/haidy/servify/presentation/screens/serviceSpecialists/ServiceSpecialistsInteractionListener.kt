package org.haidy.servify.presentation.screens.serviceSpecialists

interface ServiceSpecialistsInteractionListener {
    fun onClickBookNow(specId: String)
    fun onClickBackIcon()
    fun onClickChat(specId: String)
}