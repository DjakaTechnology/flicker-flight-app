package id.djaka.flicker.ui.home

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import id.djaka.flicker.ui.search.SearchActivity
import id.djaka.flicker.base.BasePresenter
import id.djaka.flicker.model.AirPort
import id.djaka.flicker.ui.airport.AirportActivity
import id.djaka.flicker.ui.login.LoginActivity
import id.djaka.flicker.util.*
import java.text.SimpleDateFormat
import java.util.*


class HomePresenter(homeView: HomeView) : BasePresenter<HomeView>(homeView){
    override fun onViewCreated() {
        super.onViewCreated()
    }

    fun launchSearchActivity(c:Context, airportFrom: AirPort?, airportTo: AirPort?, passanger:Int, depart:String){
        if(airportFrom == null || airportTo == null)
            return
        val i = Intent(c, SearchActivity::class.java)
        i.putExtra(AIRPORT_TO.toString(), airportTo)
        i.putExtra(AIRPORT_FROM.toString(), airportFrom)
        i.putExtra("PASSANGER", passanger)
        i.putExtra("DEPART", depart)

        putSharedPreferences(c, passanger)
        c.startActivity(i)
    }

    fun launchAirportActivity(a: Fragment, code:Int) {
        val i = Intent(a.context, AirportActivity::class.java)
        a.startActivityForResult(i, code)
    }

    fun convertDateToString(c:Calendar){
        val myFormat = "yyy-MM-dd" //In which you need put here
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

    fun addAdult(i: Int, v:EditText) {
        if((v.text.toString().toInt() <= 1 && i == -1) || (v.text.toString().toInt() >= 4 && i == 1))
            return

        v.setText((v.text.toString().toInt() + i).toString())
    }

    private fun putSharedPreferences(c:Context, passanger: Int) {
        val editor = c.getSharedPreferences(SharedKey.Session.SESSION, Context.MODE_PRIVATE).edit()

        editor.putInt(PASSANGERS.toString(), passanger)
        editor.apply()
    }


}