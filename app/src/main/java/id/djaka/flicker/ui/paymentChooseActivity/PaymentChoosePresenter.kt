package id.djaka.flicker.ui.paymentChooseActivity

import android.content.Context
import android.content.Intent
import id.djaka.flicker.ui.paymentActivity.PaymentActivity
import id.djaka.mvpanddagger.base.BasePresenter

class PaymentChoosePresenter(paymentChooseView: PaymentChooseView) : BasePresenter<PaymentChooseView>(paymentChooseView){
    fun launchPaymentActivity(c:Context) {
        c.startActivity(Intent(c, PaymentActivity::class.java))
    }

}
