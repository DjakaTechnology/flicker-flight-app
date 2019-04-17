package id.djaka.flicker.ui.detail_order

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import id.djaka.flicker.R
import id.djaka.flicker.base.BaseActivity
import id.djaka.flicker.model.Route
import id.djaka.flicker.util.SharedKey
import id.djaka.flicker.util.Utill
import kotlinx.android.synthetic.main.activity_detail_order.*
import kotlinx.android.synthetic.main.rv_flight_detail.*
import kotlinx.android.synthetic.main.rv_price_item.*

class DetailOrderActivity : BaseActivity<DetailOrderPresenter>(), DetailOrderView {
    override fun updatePriceDetail(data: Route?) {
        tv_airline.text = "Pergi (" + data!!.plane!!.airline!!.name
        tv_price.text = "Rp." + data!!.price!!
        tv_total_price.text = "Rp. " + data!!.price!! * SharedKey.getPassanger(this)
        tv_total_passager.text = "Dewasa : " + SharedKey.getPassanger(this) + "x"
    }

    override fun instantiatePresenter(): DetailOrderPresenter {
        return DetailOrderPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_order)

        prepareBtn()
        presenter.onViewCreated(intent)
    }

    private fun prepareBtn() {
        if(intent.getBooleanExtra("HIDE", false)) {
            btn_order.setOnClickListener { finish() }
            btn_order.text = "Tutup"
        }
        else
            btn_order.setOnClickListener { presenter.launchOrder(this) }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    override fun updateFlightDetail(route: Route?) {
        tv_depart_date.text = Utill.dateToShortDate(route!!.departAt!!)
        tv_from_code.text = route.airportFrom!!.code
        tv_from_city.text = route.airportFrom!!.city
        tv_from_time.text = route.departAt!!.hours.toString() + ":" + route.departAt!!.minutes.toString()

        tv_to_airport.text = route.airportTo!!.code
        tv_to_city.text = route.airportTo!!.city
        tv_to_time.text = route.arrivedAt!!.hours.toString() + ":" + route.arrivedAt!!.minutes.toString()

        tv_plane.text = route.plane!!.code

        Glide.with(this).load(route.plane.airline!!.logo).into(img_airline)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK)
            btn_order.performClick()
    }
}
