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
import android.content.res.Configuration
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
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
    val languages = listOf("English","Indonesian")



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

        fun updateUIForLanguage(language: String) {
            val locale = when (language) {
                "English" -> Locale("en")
                "Indonesian" -> Locale("id")
                else -> Locale.getDefault()
            }
            val resources = v.context.resources
            val configuration = Configuration(resources.configuration)
            configuration.setLocale(locale)
            resources.updateConfiguration(configuration, resources.displayMetrics)


            val tiketPesawatTextView = v.findViewById<TextView>(R.id.textView4)
            val dariManaTextView = v.findViewById<TextView>(R.id.dariMana)
            val keManaTextView = v.findViewById<TextView>(R.id.keMana)
            val dariTextView = v.findViewById<TextView>(R.id.tv_from_code)
            val keTextView = v.findViewById<TextView>(R.id.tv_to_airport)
            val pilihBandaraTextView = v.findViewById<TextView>(R.id.tv_from_city)
            val berangkatKapanTextView = v.findViewById<TextView>(R.id.berngkatTv)
            val masukkanTanggalBerangkatTextView:TextView = v.findViewById(R.id.et_depart)
            val penumpangTextView:TextView = v.findViewById(R.id.tv_adult)
            val cariTextView:TextView = v.findViewById(R.id.btn_search)


            tiketPesawatTextView.text = getString(R.string.tiket_pesawat)
            dariManaTextView.text = getString(R.string.dari_mana)
            keManaTextView.text = getString(R.string.ke_mana)
            dariTextView.text = getString(R.string.dari)
            keTextView.text = getString(R.string.ke)
            pilihBandaraTextView.text = getString(R.string.pilih_bandara)
            berangkatKapanTextView.text = getString(R.string.berangkat_kapan)
            masukkanTanggalBerangkatTextView.text = getString(R.string.masukkan_tanggal_berangkat)
            penumpangTextView.text = getString(R.string.penumpang)
            cariTextView.text = getString(R.string.cari)
        }


        // Initialize the language spinner and its adapter
        val languageSpinner = v.findViewById<Spinner>(R.id.languageSpinner)
        val adapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item, languages)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        languageSpinner.adapter = adapter

        // Implement a spinner selection listener
        languageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedLanguage = languages[position]
                updateUIForLanguage(selectedLanguage)
                // Do something with the selected language, for example:
                // v.tv_selected_language.text = "Selected language: $selectedLanguage"
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing here
            }
        }

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
