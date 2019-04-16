package id.djaka.flicker.ui.search

import android.content.Context
import android.content.Intent
import android.util.Log
import id.djaka.flicker.ui.detail_order.DetailOrderActivity
import id.djaka.flicker.base.BasePresenter
import id.djaka.flicker.injection.network.ApiServices
import id.djaka.flicker.model.AirPort
import id.djaka.flicker.util.AIRPORT_FROM
import id.djaka.flicker.util.AIRPORT_TO
import kotlinx.coroutines.*
import java.lang.Exception
import javax.inject.Inject

class SearchPresenter(searchView: SearchView) : BasePresenter<SearchView>(searchView){
    @Inject
    lateinit var apiServices: ApiServices

    private var route:Job? = null

    fun onViewCreated(i:Intent) {
        super.onViewCreated()

        updateText(i)
        loadRoute(i)
    }

    private fun updateText(i:Intent){
        view.updateText(
            i.getParcelableExtra<AirPort>(AIRPORT_FROM.toString()),
            i.getParcelableExtra<AirPort>(AIRPORT_TO.toString()),
            i.getStringExtra("DEPART"),
            i.getIntExtra("PASSANGER", 1)
            )
    }

    private fun loadRoute(i: Intent) {
        route = CoroutineScope(Dispatchers.IO).launch {
            val result = apiServices.getRoute(
                i.getParcelableExtra<AirPort>(AIRPORT_FROM.toString()).id!!,
                i.getParcelableExtra<AirPort>(AIRPORT_TO.toString()).id!!,
                i.getStringExtra("DEPART"))

            withContext(Dispatchers.Main){
                try {
                    val response = result.await()
                    view.updateRoute(response, i.getIntExtra("PASSANGER", 1))
                }catch (ex: Exception){
                    Log.e("TAG", ex.message)
                }
            }
        }
    }

    fun launchDetailOrderActivity(c:Context){
        val i = Intent(c, DetailOrderActivity::class.java)
        c.startActivity(i)
    }
}