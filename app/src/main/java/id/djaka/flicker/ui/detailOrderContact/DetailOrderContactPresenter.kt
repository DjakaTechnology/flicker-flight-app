package id.djaka.flicker.ui.detailOrderContact

import android.content.Context
import android.content.Intent
import id.djaka.flicker.ui.PaymentActivity
import id.djaka.flicker.ui.detailOrder.DetailOrderActivity
import id.djaka.mvpanddagger.base.BasePresenter

class DetailOrderContactPresenter(detailOrderContactView: DetailOrderContactView) : BasePresenter<DetailOrderContactView>(detailOrderContactView){
    fun launchPaymentActivity(c:Context){
        val i = Intent(c, PaymentActivity::class.java)
        c.startActivity(i)
    }
}