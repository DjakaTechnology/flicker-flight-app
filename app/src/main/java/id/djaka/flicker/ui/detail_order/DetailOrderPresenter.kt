package id.djaka.flicker.ui.detail_order

import android.app.Activity
import android.content.Context
import android.content.Intent
import id.djaka.flicker.ui.login.LoginActivity
import id.djaka.flicker.ui.detail_order_contact.DetailOrderContactActivity
import id.djaka.flicker.base.BasePresenter
import id.djaka.flicker.model.Route
import id.djaka.flicker.util.LOGIN
import id.djaka.flicker.util.ROUTE
import id.djaka.flicker.util.SharedKey

class DetailOrderPresenter(detailOrderView: DetailOrderView) : BasePresenter<DetailOrderView>(detailOrderView){
    fun launchDetailContact(c: Context){
        val i = Intent(c, DetailOrderContactActivity::class.java)
        c.startActivity(i)
    }

    fun launchLogin(c:Context){
        val i = Intent(c, LoginActivity::class.java)
        (c as Activity).startActivityForResult(i, LOGIN)
    }

    fun onViewCreated(intent: Intent?) {
        val data = intent!!.getParcelableExtra<Route>(ROUTE)
        updateFlightDetail(data)
        updatePriceDetail(data)
    }

    private fun updatePriceDetail(data: Route?) {
        view.updatePriceDetail(data)
    }

    private fun updateFlightDetail(route: Route?) {
        view.updateFlightDetail(route)
    }

    fun launchOrder(c: DetailOrderActivity) {
        if(SharedKey.getUserModel(c) != null)
            launchDetailContact(c)
        else
            launchLogin(c)
    }

}