package id.djaka.flicker.ui.home

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import id.djaka.flicker.ui.search.SearchActivity
import id.djaka.flicker.base.BasePresenter
import id.djaka.flicker.model.AirPort
import id.djaka.flicker.ui.airport.AirportActivity
import id.djaka.flicker.util.AIRPORT_FROM
import id.djaka.flicker.util.AIRPORT_TO
import java.text.SimpleDateFormat
import java.util.*


class HomePresenter(homeView: HomeView) : BasePresenter<HomeView>(homeView){
    override fun onViewCreated() {
        super.onViewCreated()
    }

    fun launchSearchActivity(c:Context, airportFrom: AirPort?, airportTo: AirPort?, passanger:Int){
        val i = Intent(c, SearchActivity::class.java)
        i.putExtra(AIRPORT_TO.toString(), airportTo)
        i.putExtra(AIRPORT_FROM.toString(), airportFrom)
        i.putExtra("PASSANGER", passanger)
        c.startActivity(i)
    }

    fun launchAirportActivity(a: Fragment, code:Int) {
        val i = Intent(a.context, AirportActivity::class.java)
        a.startActivityForResult(i, code)
    }

    fun convertDateToString(c:Calendar){
        val myFormat = "MM/dd/yy" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)

        view.updateDepart(sdf.format(c.time))
    }

    fun applyAirport(data: Intent?, code:Int) {
        val airport = data!!.getParcelableExtra<AirPort>("AIRPORT")

        if(code == 500)
            view.applyFrom(airport)
        else if(code == 550)
            view.applyTo(airport)
    }

    fun switchAirport(airportFrom: AirPort?, airportTo: AirPort?){
            if(airportFrom != null && airportTo != null){
                view.applyFrom(airportTo)
                view.applyTo(airportFrom)
            }
    }


}