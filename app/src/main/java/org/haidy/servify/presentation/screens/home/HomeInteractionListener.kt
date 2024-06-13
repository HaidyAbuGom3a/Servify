package org.haidy.servify.presentation.screens.home

interface HomeInteractionListener {
    fun onClickProfile()
    fun onClickSettings()
    fun onClickNotifications()
    fun onClickLogout()
    fun onClickShowAllServices()
    fun onClickShowAllSpecialists()
    fun onClickAddService()
    fun onClickAddCard()
    fun onClickBookNow(specialistId: String)
}