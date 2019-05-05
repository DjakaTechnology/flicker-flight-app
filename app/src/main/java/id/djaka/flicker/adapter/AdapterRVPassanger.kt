package id.djaka.flicker.adapter

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import id.djaka.flicker.R
import id.djaka.flicker.model.Passanger
import kotlinx.android.synthetic.main.rv_passanger_contact.view.*

class AdapterRVPassanger(private val context: Context) : RecyclerView.Adapter<AdapterRVPassanger.PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.rv_passanger_contact, parent, false)
        return PostViewHolder(v)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.itemView.tv_title.text = "Dewasa " + (position + 1)

        holder.itemView.et_name.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                data[position].name = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

        holder.itemView.sp_gender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                data[position].genderId = position + 1
            }
        }
    }

    private var passanger:Int = 1
    private var data = ArrayList<Passanger>()

    override fun getItemCount(): Int {
        return passanger
    }

    fun updateSeat(passanger:Int) {
        this.passanger = passanger
        data = arrayListOf()
        for (i in 1 .. passanger)
            data.add(Passanger())
        notifyDataSetChanged()
    }

    fun getData() : ArrayList<Passanger>{
        return data
    }

    inner class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}