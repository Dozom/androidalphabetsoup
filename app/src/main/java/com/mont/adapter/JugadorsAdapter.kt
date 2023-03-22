package com.example.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mont.alphabetsoup.Jugador
import com.mont.alphabetsoup.R

class JugadorsAdapter(val jugadors:List<Jugador>):RecyclerView.Adapter<JugadorsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JugadorsViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return JugadorsViewHolder(layoutInflater.inflate(R.layout.item_jugador, parent, false))
    }

    override fun onBindViewHolder(holder: JugadorsViewHolder, position: Int) {
        //aquest mètode és que va passant per cada un dels items i crida al render
        val item=jugadors[position]
        holder.render(item)

    }

    override fun getItemCount(): Int {
        return jugadors.size
    }

}