package id.djaka.flicker.ui.search

import android.content.Context
import android.content.Intent
import id.djaka.flicker.ui.detailOrder.DetailOrderActivity
import id.djaka.flicker.base.BasePresenter

class SearchPresenter(searchView: SearchView) : BasePresenter<SearchView>(searchView){
    override fun onViewCreated() {
        super.onViewCreated()
    }

    fun launchDetailOrderActivity(c:Context){
        val i = Intent(c, DetailOrderActivity::class.java)
        c.startActivity(i)
    }
}