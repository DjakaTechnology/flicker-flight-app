package id.djaka.flicker.ui.home


import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import id.djaka.flicker.R
import id.djaka.flicker.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.view.*
import android.app.DatePickerDialog
import android.content.Intent
import id.djaka.flicker.model.AirPort
import id.djaka.flicker.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_home.*
import id.djaka.flicker.util.AIRPORT_FROM
import id.djaka.flicker.util.AIRPORT_TO
import id.djaka.flicker.util.SharedKey
import java.util.*

class HomeFragment : BaseFragment<HomePresenter>(), HomeView {
    lateinit var v:View
    var airportFrom:AirPort? = null
    var airportTo:AirPort? = null

    override fun applyTo(airport: AirPort) {
        v.tv_to_airport.text = airport.code
        v.tv_to_city.text = airport.city

        airportTo = airport
    }

    override fun applyFrom(airport: AirPort) {
        v.tv_from_code.text = airport.code
        v.tv_from_city.text = airport.city

        airportFrom = airport
    }

    override fun updateDepart(s: String) { et_depart.setText(s) }

    val myCalendar = Calendar.getInstance()!!

    override fun instantiatePresenter(): HomePresenter { return HomePresenter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false)

        presenter.onViewDestroyed()

        prepareOnClickAction()
        return v
    }

    private fun prepareDatePicker() {
        val date = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            presenter.convertDateToString(myCalendar)
        }

        v.et_depart.setOnClickListener {
            DatePickerDialog(context,
                date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show() }
    }


    private fun prepareOnClickAction() {
        v.btn_search.setOnClickListener { presenter.launchSearchActivity(context, airportFrom, airportTo, et_adult.text.toString().toInt(), et_depart.text.toString()) }
        v.cl_from.setOnClickListener { presenter.launchAirportActivity(this, AIRPORT_FROM) }
        v.cl_to.setOnClickListener { presenter.launchAirportActivity(this, AIRPORT_TO) }
        v.fab_switch.setOnClickListener{presenter.switchAirport(airportFrom, airportTo)}

        v.btn_increase_adult.setOnClickListener { presenter.addAdult(1, v.et_adult) }
        v.btn_decrease_adult.setOnClickListener { presenter.addAdult(-1, v.et_adult) }

        prepareWelcomeClick()

        prepareDatePicker()
    }

    private fun prepareWelcomeClick() {
        if(SharedKey.getUserModel(context) == null) {
            v.tv_welcome.text = "Hi, Silahkan login"
            v.tv_welcome.setOnClickListener{ (activity as MainActivity).launchLogin()}
        }else{
            v.tv_welcome.text = "Hi, ${SharedKey.getUserModel(context)!!.name!!.substringBefore(" ")}"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if((requestCode == AIRPORT_FROM || requestCode == AIRPORT_TO) && resultCode == Activity.RESULT_OK)
            presenter.applyAirport(data, requestCode)
    }
}
