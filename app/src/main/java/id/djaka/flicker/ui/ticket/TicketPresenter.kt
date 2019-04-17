package id.djaka.flicker.ui.ticket

import android.content.Context
import android.util.Log
import id.djaka.flicker.base.BasePresenter
import id.djaka.flicker.injection.network.ApiServices
import id.djaka.flicker.util.SharedKey
import kotlinx.coroutines.*
import java.lang.Exception
import javax.inject.Inject


class TicketPresenter(ticketView: TicketView) : BasePresenter<TicketView>(ticketView){

    @Inject
    lateinit var apiServices: ApiServices

    var job: Job? = null

    fun onViewCreated(context: Context) {
        loadReservation(context)
    }

    private fun loadReservation(c:Context) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val result = apiServices.getReservations(SharedKey.getUserModel(c)!!.id!!)
            withContext(Dispatchers.Main){
                try {
                    val response = result.await()
                    view.updateReservation(response)
                }catch (ex: Exception){
                    Log.e("TAG", ex.message)
                }
            }
        }
    }


}