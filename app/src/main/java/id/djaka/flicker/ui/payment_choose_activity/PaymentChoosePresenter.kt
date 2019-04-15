package id.djaka.flicker.ui.payment_choose_activity

import android.content.Context
import android.content.Intent
import id.djaka.flicker.ui.payment_activity.PaymentActivity
import id.djaka.flicker.base.BasePresenter

class PaymentChoosePresenter(paymentChooseView: PaymentChooseView) : BasePresenter<PaymentChooseView>(paymentChooseView){
    fun launchPaymentActivity(c:Context) {
        c.startActivity(Intent(c, PaymentActivity::class.java))
    }

}
