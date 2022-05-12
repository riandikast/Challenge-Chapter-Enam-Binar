package com.binar.challengechapterenam.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binar.challengechapterenam.R
import com.binar.challengechapterenam.database.FavoriteFilm
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_fav.view.*
import kotlinx.android.synthetic.main.item_film.view.*

class AdapterFavorite (var onclick : (FavoriteFilm)-> Unit) : RecyclerView.Adapter<AdapterFavorite.ViewHolder>() {
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view)
    var datafav : List<FavoriteFilm>? = null
    fun setDataFav(fav  : List<FavoriteFilm>){
        this.datafav = fav
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemview = LayoutInflater.from(parent.context).inflate(R.layout.item_fav, parent,false)
        return ViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(datafav!![position].image).into(holder.itemView.gambarv)

        holder.itemView.text1v.text = datafav!![position].title
        holder.itemView.text2v.text = datafav!![position].director
        holder.itemView.text3v.text = datafav!![position].releaseDate

        holder.itemView.cardv.setOnClickListener {
            onclick(datafav!![position])
        }
    }

    override fun getItemCount(): Int {
        if (datafav == null){
            return 0
        }else{
            return datafav!!.size
        }
    }
}