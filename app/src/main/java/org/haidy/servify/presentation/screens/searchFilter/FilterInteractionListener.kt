package org.haidy.servify.presentation.screens.searchFilter

interface FilterInteractionListener {
    fun onClickBackIcon()
    fun onClickService(serviceName: String)
    fun onClickRating(rating: String)
    fun onNameChanged(name: String)
    fun onClickApplyFilter()
    fun onClickResetFilter()
}