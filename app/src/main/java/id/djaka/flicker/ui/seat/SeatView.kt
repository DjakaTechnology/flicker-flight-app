package id.djaka.flicker.ui.seat

import id.djaka.flicker.base.BaseView
import id.djaka.flicker.model.Passanger

interface SeatView : BaseView {
    fun loadPassanger(data: ArrayList<Passanger>?)
    fun loadSeat(data: List<Passanger>)

}