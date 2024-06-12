package org.haidy.servify.presentation.screens.bookingAppointment

interface BookingAppointmentInteractionListener {
    fun onRequiredTasksChanged(text: String)
    fun onClickBackIcon()
    fun onClickBookNow()
    fun onPriceChanged(price: String)
    fun onChooseTime(time: String)
    fun onChooseDate(date: String)
}