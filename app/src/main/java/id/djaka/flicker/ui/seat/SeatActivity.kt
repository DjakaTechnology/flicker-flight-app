package id.djaka.flicker.ui.seat

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import id.djaka.flicker.R
import id.djaka.flicker.adapter.AdapterRVPassangerSeat
import id.djaka.flicker.adapter.AdapterRVSeat
import id.djaka.flicker.base.BaseActivity
import id.djaka.flicker.model.Passanger
import id.djaka.flicker.util.SharedKey
import kotlinx.android.synthetic.main.activity_seat.*

class SeatActivity : BaseActivity<SeatPresenter>(), SeatView {
    val adapter = AdapterRVSeat(this)
    val adapterPassanger = AdapterRVPassangerSeat(this)
    var passangers = arrayListOf<Passanger>()
    var currentPassangerIndex = 0

    override fun loadSeat(data: List<Passanger>) {
        adapter.updateSeat(SharedKey.getRoute(this)!!.plane!!, data)

        setCurrentPassanger(0)
    }

    fun setCurrentPassanger(i:Int) {
        currentPassangerIndex = i
        adapter.setCurrentPassanger(i)
    }

    override fun loadPassanger(data: ArrayList<Passanger>?) {
        adapterPassanger.updatePassanger(data!!)
        passangers = data
        for(i in 0 until data.size)
            passangers[i].id = i * -1
    }

    fun getCurrentPassanger(i:Int) : Passanger{
        return passangers[i]
    }

    fun updatePassanger(seatCode:String){
        passangers[currentPassangerIndex].seatCode = seatCode
        adapterPassanger.notifyDataSetChanged()
    }

    override fun instantiatePresenter(): SeatPresenter {
        return SeatPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat)

        presenter.onViewCreated(this)
        prepareRV()
        prepareOnClick()
    }

    private fun prepareOnClick() {
        btn_next.setOnClickListener { presenter.launchPayment(this, passangers) }
    }

    private fun prepareRV() {
        rv_seat.adapter = adapter
        rv_seat.layoutManager = GridLayoutManager(this, SharedKey.getRoute(this)!!.plane!!.seatColumn!!)

        rv_passanger.layoutManager = LinearLayoutManager(this)
        rv_passanger.adapter = adapterPassanger
    }
}
