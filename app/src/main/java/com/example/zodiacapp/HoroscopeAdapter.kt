package com.example.zodiacapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.zodiacapp.data.Horoscope
import com.example.zodiacapp.utils.SessionManager


class HoroscopeAdapter(var items: List<Horoscope>, val onItemClick: (Int) -> Unit) : Adapter<HoroscopeViewHolder>() {

    //cual es la vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_horoscope, parent, false)
        return HoroscopeViewHolder(view)
    }

    //cuantos elementos tengo que listar
    override fun getItemCount(): Int {
        return items.size
    }

    //voy a mostar la celda en la poscicion
    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        val horoscope = items[position]
        holder.render(horoscope)
        holder.itemView.setOnClickListener{
            //voy a navegar al detalle
            onItemClick(position)
        }
    }

    fun updateItems(items: List<Horoscope>){
        this.items = items
        notifyDataSetChanged()
    }
}

class HoroscopeViewHolder(view: View) : ViewHolder(view) {

    val nameTextView: TextView = view.findViewById(R.id.nameTextView)
    val dateTextView: TextView = view.findViewById(R.id.dateTextView)
    val iconImageView: ImageView = view.findViewById(R.id.iconImageView)
    val favoriteImage: ImageView = view.findViewById(R.id.favoriteImageView)

    fun render(horoscope: Horoscope) {

        nameTextView.setText(horoscope.name)
        dateTextView.setText(horoscope.dates)
        iconImageView.setImageResource(horoscope.icon)

        val session = SessionManager(itemView.context)

        if (session.getFavoriteHoroscope()== horoscope.id){

            favoriteImage.visibility = View.VISIBLE

        } else {
            favoriteImage.visibility = View.GONE
        }

    }

}
