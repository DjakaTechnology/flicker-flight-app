package id.djaka.flicker.ui.search

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.djaka.flicker.R
import id.djaka.flicker.adapter.AdapterRVRoute
import id.djaka.flicker.base.BaseActivity
import id.djaka.flicker.model.AirPort
import id.djaka.flicker.model.Route
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.rv_ticket_search.*

class SearchActivity : BaseActivity<SearchPresenter>(), SearchView{
    override fun updateText(from: AirPort?, to: AirPort?, depart: String?, passanger: Int) {
        tv_from_city.text = from!!.city
        tv_from_code.text = from!!.code

        tv_to_city.text = to!!.city
        tv_to_code.text = to!!.code

        tv_date.text = depart
        tv_passanger.text = "Penumpang :" + passanger.toString()
    }

    val adapter = AdapterRVRoute(this)
    override fun updateRoute(data: List<Route>, passanger: Int) {
        adapter.updateAirport(data, passanger)
    }

    override fun instantiatePresenter(): SearchPresenter {
        return SearchPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        presenter.onViewCreated(intent)

        prepareButton()
        prepareRV()
    }

    private fun prepareRV() {
        rv_route.adapter = adapter
        rv_route.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    private fun prepareButton() {

    }
}
