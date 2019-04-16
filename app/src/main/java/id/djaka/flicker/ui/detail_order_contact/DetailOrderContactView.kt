package id.djaka.flicker.ui.detail_order_contact

import id.djaka.flicker.base.BaseView
import id.djaka.flicker.model.User

interface DetailOrderContactView : BaseView {
    fun fillEditText(userModel: User?)
    fun renderContact(passanger: Int)
}