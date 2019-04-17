package id.djaka.flicker.ui.seat

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.util.Log
import id.djaka.flicker.SuccessActivity
import id.djaka.flicker.base.BasePresenter
import id.djaka.flicker.injection.network.ApiServices
import id.djaka.flicker.model.Passanger
import id.djaka.flicker.util.PASSANGERS
import id.djaka.flicker.util.SharedKey
import kotlinx.coroutines.*
import java.lang.Exception
import java.util.ArrayList
import javax.inject.Inject

class SeatPresenter(loginView: SeatView) : BasePresenter<SeatView>(loginView){
    @Inject
    lateinit var apiServices:ApiServices

    private var job :Job? = null
    var dialog: ProgressDialog? = null

    fun onViewCreated(c:Activity) {
        loadPassanger(c.intent.getParcelableArrayListExtra<Passanger>(PASSANGERS.toString()))
        loadRecyclerView(c)
    }

    private fun loadPassanger(data: ArrayList<Passanger>?) {
        view.loadPassanger(data)
    }

    private fun loadRecyclerView(c:Activity){
        dialog = ProgressDialog.show(c, "",
            "Memproses pesanan anda...", true)

        job = CoroutineScope(Dispatchers.IO).launch {
            val result = apiServices.getTaken(SharedKey.getRoute(c)!!.id!!)

            withContext(Dispatchers.Main){
                dialog!!.dismiss()
                try {
                    val response = result.await()
                    view.loadSeat(response)
                }catch (ex: Exception){
                    Log.e("TAG", ex.message)
                }
            }
        }
    }

    fun launchPayment(c:Context, data: ArrayList<Passanger>?) {
        val customerId = SharedKey.getUserModel(c)!!.id
        val rute = SharedKey.getRoute(c)!!
        dialog = ProgressDialog.show(c, "",
            "Memproses pesanan anda...", true)

        job =  CoroutineScope(Dispatchers.IO).launch {
            try{
                for (p in data!!) {
                    apiServices.insertReservation(customerId!!, p.seatCode!!, rute.id!!, p.name!!, p.genderId!!).await()

                    withContext(Dispatchers.Main){
                        Log.d("SUCCESS", p.seatCode)
                    }
                }
            }catch (e:Exception){
                Log.e("ERROR", e.message)
            }

            dialog!!.dismiss()

            withContext(Dispatchers.Main){
                launchSuccess(c)
            }
        }
    }

    fun launchSuccess(c:Context){
        val i = Intent(c, SuccessActivity::class.java)
        c.startActivity(i)
    }

}