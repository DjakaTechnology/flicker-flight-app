package id.djaka.flicker.ui.payment_activity

import id.djaka.flicker.base.BaseView
import id.djaka.flicker.model.Reservation

interface PaymentView : BaseView {
    fun showImage(body: Reservation)
}