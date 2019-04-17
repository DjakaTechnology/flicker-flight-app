package id.djaka.flicker.ui.ticket

import android.content.Intent
import id.djaka.flicker.base.BaseView
import id.djaka.flicker.model.AirPort
import id.djaka.flicker.model.Reservation

interface TicketView : BaseView {
    fun updateReservation(response: List<Reservation>)
}