package id.djaka.flicker.ui.detailOrderContact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.djaka.flicker.R
import id.djaka.flicker.ui.detailOrder.DetailOrderActivity
import id.djaka.flicker.ui.detailOrder.DetailOrderPresenter
import id.djaka.flicker.ui.detailOrder.DetailOrderView
import id.djaka.mvpanddagger.base.BaseActivity
import id.djaka.mvpanddagger.base.BasePresenter
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
