package id.djaka.flicker.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import id.djaka.flicker.R
import id.djaka.flicker.model.AirPort
import id.djaka.flicker.model.Reservation
import id.djaka.flicker.ui.detail_order.DetailOrderActivity
import id.djaka.flicker.ui.payment_activity.PaymentActivity
import id.djaka.flicker.util.RESERVATION
import id.djaka.flicker.util.ROUTE
import id.djaka.flicker.util.SharedKey
import id.djaka.flicker.util.Utill
import kotlinx.android.synthetic.main.rv_tickets.view.*

class AdapterRVReservation(private val context: Context) : RecyclerView.Adapter<AdapterRVReservation.PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.rv_tickets, parent, false)
        return PostViewHolder(v)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.itemView.tv_status.text = data[position].status!!.name
        holder.itemView.tv_seat.text = data[position].seatCode
        holder.itemView.tv_res_code.text = data[position].resCode
        if(data[position].statusId == 2){
            holder.itemView.cl_status_bg.setBackgroundColor(ContextCompat.getColor(context, R.color.green))
            holder.itemView.img_status.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_checked))

            holder.itemView.cl_card.setOnClickListener {
                val i = Intent(context, DetailOrderActivity::class.java)
                i.putExtra(ROUTE, data[holder.adapterPosition].route)
                i.putExtra("HIDE", true)

                putSharedPreferences(context, Gson().toJson(data[holder.adapterPosition].route))
                context.startActivity(i) }
        }else if(data[position].statusId == 3){
            holder.itemView.cl_status_bg.setBackgroundColor(ContextCompat.getColor(context, R.color.red))
            holder.itemView.tv_status.text = data[position].status!!.name

//            if(data[position].tryCount!! < 1) {
//                holder.itemView.tv_status.text = "Reupload"
//                holder.itemView.img_status.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_clock))
//            }else
                holder.itemView.img_status.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_close))

            holder.itemView.cl_card.setOnClickListener {
                val i = Intent(context, PaymentActivity::class.java)
                i.putExtra(RESERVATION.toString(), data[holder.adapterPosition])
                context.startActivity(i)
            }
        }else{
            holder.itemView.cl_card.setOnClickListener {
                val i = Intent(context, PaymentActivity::class.java)
                i.putExtra(RESERVATION.toString(), data[holder.adapterPosition])
                context.startActivity(i)
            }
        }

        Glide.with(context).load(data[position].route!!.plane!!.airline!!.logo).into(holder.itemView.img_logo)

        holder.itemView.tv_cost.text = data[position].cost.toString()
        holder.itemView.tv_date.text = Utill.dateToShortDate(data[position].departAt!!)
        holder.itemView.tv_time.text = Utill.dateToHour(data[position].departAt!!) + " - " + Utill.dateToHour(data[position].route!!.arrivedAt!!)
        holder.itemView.tv_passanger.text = data[position].name

        holder.itemView.tv_from_city.text = data[position].route!!.airportFrom!!.city
        holder.itemView.tv_from_code.text = data[position].route!!.airportFrom!!.code

        holder.itemView.tv_to_city.text = data[position].route!!.airportTo!!.city
        holder.itemView.tv_to_code.text = data[position].route!!.airportTo!!.code
    }

    private fun putSharedPreferences(c:Context, json: String) {
        val editor = c.getSharedPreferences(SharedKey.Session.SESSION, Context.MODE_PRIVATE).edit()

        editor.putString(ROUTE, json)
        editor.apply()
    }

    private var data: List<Reservation> = listOf()

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateReservation(data: List<Reservation>) {
        this.data = data
        notifyDataSetChanged()
    }

    inner class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}