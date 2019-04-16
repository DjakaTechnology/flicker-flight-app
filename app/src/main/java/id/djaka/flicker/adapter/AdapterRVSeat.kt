package id.djaka.flicker.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.djaka.flicker.R
import id.djaka.flicker.model.Plane
import id.djaka.flicker.model.Route
import id.djaka.flicker.ui.detail_order.DetailOrderActivity
import id.djaka.flicker.util.ROUTE
import kotlinx.android.synthetic.main.rv_seat.view.*

class AdapterRVSeat(private val context: Context) : RecyclerView.Adapter<AdapterRVSeat.PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.rv_seat, parent, false)
        return PostViewHolder(v)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        if(position == 0){
            holder.itemView.tv_code.setTextColor(Color.WHITE)
            holder.itemView.btn_seat.setBackgroundColor(Color.WHITE)
        }
    }

    private var data: Plane = Plane()
    private var passanger:Int = 0

    override fun getItemCount(): Int {
        return data.seatColumn!! * data.seatRow!!
    }

    fun updateSeat(data: Plane, passanger:Int) {
        this.data = data
        this.passanger = passanger
        notifyDataSetChanged()
    }

    inner class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}