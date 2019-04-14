package id.djaka.flicker.ui.airport

import android.util.Log
import id.djaka.flicker.base.BasePresenter
import id.djaka.flicker.injection.network.ApiServices
import kotlinx.coroutines.*
import java.lang.Exception
import javax.inject.Inject

class AirportPresenter(airportView: AirportView) : BasePresenter<AirportView>(airportView){
    @Inject
    lateinit var apiServices: ApiServices

    private var airport:Job? = null
    override fun onViewCreated() {
        super.onViewCreated()
        loadAirport()
    }

    private fun loadAirport(){
        airport = CoroutineScope(Dispatchers.IO).launch {
            val result = apiServices.getAirport()

            withContext(Dispatchers.Main){
                try {
                    val response = result.await()
                    view.updateAirport(response)
                }catch (ex:Exception){
                    Log.e("TAG", ex.message)
                }
            }
        }
    }
}