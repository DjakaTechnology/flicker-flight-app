package id.djaka.flicker.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.djaka.flicker.R
import id.djaka.flicker.model.Route
import id.djaka.flicker.ui.detailOrder.DetailOrderActivity
import id.djaka.flicker.util.ROUTE
import kotlinx.android.synthetic.main.rv_ticket_search.view.*

class AdapterRVRoute(private val context: Context) : RecyclerView.Adapter<AdapterRVRoute.PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.rv_ticket_search, parent, false)
        return PostViewHolder(v)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.itemView.tv_cost.text = "Rp."+ data[position].price
        holder.itemView.tv_depart.text = data[position].departAt
        holder.itemView.tv_total_cost.text = "Rp."+ (data[position].price!! * passanger)
        Glide.with(context).load(data[position].plane!!.airline!!.logo).into(holder.itemView.img_logo)

        holder.itemView.card_item.setOnClickListener {
            val i = Intent(context, DetailOrderActivity::class.java)
            i.putExtra(ROUTE, data[holder.adapterPosition])
            context.startActivity(i)
        }
    }

    private var data: List<Route> = listOf()
    private var passanger:Int = 0

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateAirport(data: List<Route>, passanger:Int) {
        this.data = data
        this.passanger = passanger
        notifyDataSetChanged()
    }

    inner class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}