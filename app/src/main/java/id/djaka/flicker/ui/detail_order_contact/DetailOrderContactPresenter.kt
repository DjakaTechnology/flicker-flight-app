package id.djaka.flicker.ui.detail_order_contact

import android.content.Context
import android.content.Intent
import android.util.Log
import id.djaka.flicker.ui.seat.SeatActivity
import id.djaka.flicker.base.BasePresenter
import id.djaka.flicker.model.Passanger
import id.djaka.flicker.util.PASSANGERS
import id.djaka.flicker.util.SharedKey

class DetailOrderContactPresenter(detailOrderContactView: DetailOrderContactView) : BasePresenter<DetailOrderContactView>(detailOrderContactView){
    fun launchSeatActivity(c: Context, data: ArrayList<Passanger>){
        val i = Intent(c, SeatActivity::class.java)
        i.putParcelableArrayListExtra(PASSANGERS.toString(), data)
        c.startActivity(i)
    }

    fun onViewCreated(c:Context) {
        view.fillEditText(SharedKey.getUserModel(c))
        view.renderContact(SharedKey.getPassanger(c))
    }
}