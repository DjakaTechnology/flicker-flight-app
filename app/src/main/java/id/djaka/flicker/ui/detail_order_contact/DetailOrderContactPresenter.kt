package id.djaka.flicker.ui.detail_order_contact

import android.app.AlertDialog
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
        for (i in data){
            if(i.name == "") {
                showAlert(c, "Isi data", "Silahkan isi data yang diperlukan")
                return
            }
        }
        val i = Intent(c, SeatActivity::class.java)
        i.putParcelableArrayListExtra(PASSANGERS.toString(), data)
        c.startActivity(i)
    }

    fun onViewCreated(c:Context) {
        view.fillEditText(SharedKey.getUserModel(c))
        view.renderContact(SharedKey.getPassanger(c))
    }

    fun showAlert(c: Context, title:String, message:String) {
        val alertDialog = android.app.AlertDialog.Builder(c).create()
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setButton(
            AlertDialog.BUTTON_NEUTRAL, "OK"
        ) { dialog, which -> dialog.dismiss() }
        alertDialog.show()
    }
}