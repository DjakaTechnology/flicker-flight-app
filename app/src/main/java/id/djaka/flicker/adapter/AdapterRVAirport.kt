package id.djaka.flicker.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.djaka.flicker.R
import id.djaka.flicker.model.AirPort
import kotlinx.android.synthetic.main.rv_airport_item.view.*

class AdapterRVAirport(private val context: Context) : RecyclerView.Adapter<AdapterRVAirport.PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.rv_airport_item, parent, false)
        return PostViewHolder(v)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.itemView.tv_name.text = data[position].name
        holder.itemView.tv_city.text = data[position].city
        holder.itemView.tv_code.text = data[position].code

        holder.itemView.card_item.setOnClickListener {
            val i = Intent()
            i.putExtra("AIRPORT", data[holder.adapterPosition])
            (context as Activity).setResult(Activity.RESULT_OK, i)
            context.finish()
        }
    }

    private var data: List<AirPort> = listOf()

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateAirport(airport: List<AirPort>) {
        this.data = airport
        notifyDataSetChanged()
    }

    inner class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}