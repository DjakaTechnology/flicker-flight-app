package id.djaka.flicker.ui.home

import android.content.Context
import android.content.Intent
import id.djaka.flicker.ui.search.SearchActivity
import id.djaka.mvpanddagger.base.BasePresenter

class HomePresenter(homeView: HomeView) : BasePresenter<HomeView>(homeView){
    override fun onViewCreated() {
        super.onViewCreated()
    }

    fun launchSearchActivity(c:Context){
        val i = Intent(c, SearchActivity::class.java)
        c.startActivity(i)
    }

}