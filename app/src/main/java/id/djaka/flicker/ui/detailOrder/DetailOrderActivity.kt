package id.djaka.flicker.ui.detailOrder

import android.os.Bundle
import id.djaka.flicker.R
import id.djaka.flicker.base.BaseActivity
import kotlinx.android.synthetic.main.activity_detail_order.*

class DetailOrderActivity : BaseActivity<DetailOrderPresenter>(), DetailOrderView {
    override fun instantiatePresenter(): DetailOrderPresenter {
        return DetailOrderPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_order)

        prepareBtn()
    }

    private fun prepareBtn() {
        btn_order.setOnClickListener { presenter.launchDetailContact(this) }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }
}
