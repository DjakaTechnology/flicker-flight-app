package id.djaka.flicker.ui.detail_order_contact

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.djaka.flicker.R
import id.djaka.flicker.adapter.AdapterRVPassanger
import id.djaka.flicker.base.BaseActivity
import id.djaka.flicker.model.User
import kotlinx.android.synthetic.main.activity_detail_order_contact.*

class DetailOrderContactActivity : BaseActivity<DetailOrderContactPresenter>(), DetailOrderContactView {
    override fun renderContact(passanger: Int) {
        adapter.updateSeat(passanger)
    }

    private val adapter = AdapterRVPassanger(this)
    override fun fillEditText(userModel: User?) {
        et_email.setText(userModel!!.email)
        et_name.setText(userModel!!.name)
        et_phone.setText(userModel!!.phone)
        et_name.setText(userModel!!.name)
        sp_gender.setSelection(userModel.genderId!! - 1)
    }

    override fun instantiatePresenter(): DetailOrderContactPresenter {
        return DetailOrderContactPresenter(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_order_contact)

        presenter.onViewCreated(this)

        prepareBtn()
        prepareRV()
    }

    private fun prepareRV() {
        rv_passanger.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_passanger.adapter = adapter
    }

    private fun prepareBtn() {
        btn_seat.setOnClickListener { presenter.launchSeatActivity(this, adapter.getData()) }
    }
}
