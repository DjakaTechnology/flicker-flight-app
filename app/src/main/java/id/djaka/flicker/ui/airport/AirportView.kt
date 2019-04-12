package id.djaka.flicker.ui.airport

import id.djaka.flicker.base.BaseView
import id.djaka.flicker.model.AirPort

interface AirportView : BaseView{
    fun updateAirport(data : List<AirPort>)
}