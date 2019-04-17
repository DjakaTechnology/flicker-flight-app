package id.djaka.flicker.ui.airport

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.djaka.flicker.R
import id.djaka.flicker.adapter.AdapterRVAirport
import id.djaka.flicker.base.BaseActivity
import id.djaka.flicker.model.AirPort
import kotlinx.android.synthetic.main.activity_airport.*

class AirportActivity : BaseActivity<AirportPresenter>(), AirportView {

    val adapter = AdapterRVAirport(this)

    override fun instantiatePresenter(): AirportPresenter {
        return AirportPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_airport)

        prepareRV()

        presenter.onViewCreated()
    }

    private fun prepareRV() {
        rv_airport.adapter = adapter
        rv_airport.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.options_menu, menu)
//
//        return true
//    }

    override fun updateAirport(data: List<AirPort>) {
        loading.visibility = View.GONE
        adapter.updateAirport(data)
    }

    fun back(v:View?){
        onBackPressed()
    }


}
