package id.djaka.flicker.ui.payment_choose_activity

import android.os.Bundle
import id.djaka.flicker.R
import id.djaka.flicker.base.BaseActivity
import kotlinx.android.synthetic.main.rv_payment_item.*

class PaymentChooseActivity : BaseActivity<PaymentChoosePresenter>(), PaymentChooseView {
    override fun instantiatePresenter(): PaymentChoosePresenter {
        return PaymentChoosePresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_choose)

        prepareBtn()
    }

    private fun prepareBtn() {
        cl_payment_item.setOnClickListener { presenter.launchPaymentActivity(getContext())}
    }
}
