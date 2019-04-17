package id.djaka.flicker.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import id.djaka.flicker.R
import id.djaka.flicker.model.Passanger
import id.djaka.flicker.ui.seat.SeatActivity
import kotlinx.android.synthetic.main.rv_passanger_seat.view.*

class AdapterRVPassangerSeat(private val context: SeatActivity) : RecyclerView.Adapter<AdapterRVPassangerSeat.PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.rv_passanger_seat, parent, false)
        return PostViewHolder(v)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.itemView.tv_code.text = data[position].seatCode
        holder.itemView.tv_name.text = data[position].name

        holder.itemView.btn_seat.setOnClickListener {
            makeTaken(holder.itemView)
            context.setCurrentPassanger(position)
        }
    }

    private fun makeTaken(itemView: View) {
        itemView.btn_seat.background = ContextCompat.getDrawable(context, R.drawable.ic_seat_taken)
        itemView.tv_code.setTextColor(Color.WHITE)
        if(current != null)
            makeAvailable(current!!)
        current = itemView
    }

    private fun makeAvailable(itemView: View) {
        itemView.btn_seat.background = ContextCompat.getDrawable(context, R.drawable.ic_seat)
        itemView.tv_code.setTextColor(ContextCompat.getColor(context, R.color.black_main))
    }

    var current:View? = null

    private var data = ArrayList<Passanger>()

    override fun getItemCount(): Int {
        return data.size
    }

    fun updatePassanger(data:ArrayList<Passanger>) {
        this.data = data

        notifyDataSetChanged()
    }

    fun getData() : ArrayList<Passanger>{
        return data
    }

    inner class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}