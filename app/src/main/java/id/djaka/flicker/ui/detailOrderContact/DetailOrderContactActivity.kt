package id.djaka.flicker.ui.detailOrderContact

import android.os.Bundle
import id.djaka.flicker.R
import id.djaka.flicker.base.BaseActivity
import kotlinx.android.synthetic.main.activity_detail_order_contact.*

class DetailOrderContactActivity : BaseActivity<DetailOrderContactPresenter>(), DetailOrderContactView {
    override fun instantiatePresenter(): DetailOrderContactPresenter {
        return DetailOrderContactPresenter(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_order_contact)

        prepareBtn()
    }

    private fun prepareBtn() {
        btn_pay.setOnClickListener { presenter.launchPaymentActivity(this) }
    }
}
