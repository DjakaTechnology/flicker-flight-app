package id.djaka.flicker.ui.paymentActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.djaka.flicker.R
import id.djaka.mvpanddagger.base.BaseActivity
import kotlinx.android.synthetic.main.activity_payment.*

class PaymentActivity : BaseActivity<PaymentPresenter>(), PaymentView {
    override fun instantiatePresenter(): PaymentPresenter {
        return PaymentPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        prepareBtn()
    }

    private fun prepareBtn() {
        fl_payment_ib.setOnClickListener { el_payment_ib.expand() }
    }
}
