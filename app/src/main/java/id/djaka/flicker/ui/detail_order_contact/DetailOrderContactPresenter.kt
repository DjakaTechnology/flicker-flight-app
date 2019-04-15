package id.djaka.flicker.ui.detail_order_contact

import android.content.Context
import android.content.Intent
import id.djaka.flicker.ui.payment_choose_activity.PaymentChooseActivity
import id.djaka.flicker.base.BasePresenter

class DetailOrderContactPresenter(detailOrderContactView: DetailOrderContactView) : BasePresenter<DetailOrderContactView>(detailOrderContactView){
    fun launchPaymentActivity(c:Context){
        val i = Intent(c, PaymentChooseActivity::class.java)
        c.startActivity(i)
    }
}