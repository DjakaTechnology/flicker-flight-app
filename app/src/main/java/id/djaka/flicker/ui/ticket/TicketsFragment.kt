package id.djaka.flicker.ui.ticket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.djaka.flicker.R
import id.djaka.flicker.adapter.AdapterRVReservation
import id.djaka.flicker.base.BaseFragment
import id.djaka.flicker.model.Reservation
import kotlinx.android.synthetic.main.fragment_tickets.view.*

class TicketsFragment : BaseFragment<TicketPresenter>(), TicketView {
    override fun updateReservation(response: List<Reservation>) {
        adapter!!.updateReservation(response)
        v.loading.visibility = View.INVISIBLE
    }

    lateinit var v:View
    var adapter:AdapterRVReservation? = null

    override fun instantiatePresenter(): TicketPresenter {
        return TicketPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_tickets, container, false)

        adapter = AdapterRVReservation(context)
        presenter.onViewCreated(context)
        prepareRV()
        return v
    }

    private fun prepareRV() {
        v.rv_reservation.adapter = adapter
        v.rv_reservation.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }
}
