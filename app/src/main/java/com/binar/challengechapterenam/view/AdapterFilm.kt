package com.binar.challengechapterenam.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binar.challengechapterenam.R
import com.binar.challengechapterenam.database.FavoriteDB
import com.binar.challengechapterenam.database.FavoriteFilm
import com.binar.challengechapterenam.model.GetAllFilmItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.item_film.view.*
import kotlinx.android.synthetic.main.item_film.view.text1
import kotlinx.android.synthetic.main.item_film.view.text2
import kotlinx.android.synthetic.main.item_film.view.text3
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AdapterFilm (private val onclick : (GetAllFilmItem)->Unit) : RecyclerView.Adapter<AdapterFilm.ViewHolder>() {
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view)
    var datafilm : List<GetAllFilmItem>? = null


    fun setDataFilm(film  : List<GetAllFilmItem>){
        this.datafilm = film
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemview = LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent,false)
        return ViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(datafilm!![position].image).into(holder.itemView.gambar)

        holder.itemView.text1.text = datafilm!![position].title
        holder.itemView.text2.text = datafilm!![position].director
        holder.itemView.text3.text = datafilm!![position].releaseDate

        holder.itemView.card.setOnClickListener {
            onclick(datafilm!![position])

        }
    }

    override fun getItemCount(): Int {
        if (datafilm == null){
            return 0
        }else{
            return datafilm!!.size
        }
    }

}