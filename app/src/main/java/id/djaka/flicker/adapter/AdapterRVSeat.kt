package id.djaka.flicker.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import id.djaka.flicker.R
import id.djaka.flicker.model.Passanger
import id.djaka.flicker.model.Plane
import id.djaka.flicker.ui.seat.SeatActivity
import kotlinx.android.synthetic.main.rv_seat.view.*

class AdapterRVSeat(private val context: SeatActivity) : RecyclerView.Adapter<AdapterRVSeat.PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.rv_seat, parent, false)
        return PostViewHolder(v)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        if(plane.seatColumn != 0)
            currentRow = position / plane.seatColumn!!

        holder.itemView.tv_code.setTextColor(Color.BLACK)
        holder.itemView.tv_code.text = (currentRow + 65).toChar() + "" + (position + 1)

        if(data.any{it.seatCode ==  holder.itemView.tv_code.text }){
            holder.itemView.btn_seat.background = ContextCompat.getDrawable(context, R.drawable.ic_seat_unavailable)
            holder.itemView.tv_code.setTextColor(Color.WHITE)
            holder.itemView.btn_seat.isEnabled = false
        }

        holder.itemView.btn_seat.setOnClickListener {
            makeTaken(holder.itemView)
        }
    }

    private fun makeAvailable(itemView: View) {
        itemView.btn_seat.background = ContextCompat.getDrawable(context, R.drawable.ic_seat)
        itemView.tv_code.setTextColor(ContextCompat.getColor(context, R.color.black_main))
        itemView.btn_seat.isEnabled = true
    }

    private fun makeTaken(itemView: View) {
        itemView.btn_seat.background = ContextCompat.getDrawable(context, R.drawable.ic_seat_taken)
        itemView.tv_code.setTextColor(Color.WHITE)
        itemView.btn_seat.isEnabled = false

        try{
            makeAvailable(passangerView[passanger.id!!]!!)
        }catch (e:Exception){

        }

        passangerView[passanger.id!!] = itemView
        passanger.seatCode = itemView.tv_code.text.toString()
        context.updatePassanger(passanger.seatCode.toString())
    }


    private var plane: Plane = Plane()
    private var data = listOf<Passanger>()
    private var passanger = Passanger()
    private var passangerView = hashMapOf<Int, View>()
    private var currentRow = 1

    override fun getItemCount(): Int {
        return (plane.seatColumn!! * plane.seatRow!!)
    }

    fun updateSeat(plane: Plane, data:List<Passanger>) {
        this.plane = plane
        this.data = data
        notifyDataSetChanged()
    }

    fun setCurrentPassanger(i:Int){
        passanger = context.getCurrentPassanger(i)
    }

    inner class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}