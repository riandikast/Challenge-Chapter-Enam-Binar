package com.binar.challengechapterenam.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.binar.challengechapterenam.R
import com.binar.challengechapterenam.database.FavoriteDB
import com.binar.challengechapterenam.database.FavoriteFilm
import com.binar.challengechapterenam.model.GetAllFilmItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async


class DetailFragment : Fragment() {
    lateinit var favorite : String
    var db: FavoriteDB? = null
    var film : FavoriteFilm? = null
    lateinit var id : String
    lateinit var title : String
    lateinit var director : String
    lateinit var createdAt : String
    lateinit var synopsis : String
    lateinit var image: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        val getfilm = arguments?.getParcelable<GetAllFilmItem>("detailfilm")
        val getfilmfromfav = arguments?.getParcelable<FavoriteFilm>("detailfilmfromfav")
        db = FavoriteDB.getInstance(requireActivity())

        if (getfilm != null){
            view.text1.text = getfilm.title
            view.text2.text = getfilm.director
            view.text3.text = getfilm.createdAt
            view.text4.text = getfilm.synopsis
            Glide.with(requireContext()).load(getfilm.image).into(view.gambar1)

            id = getfilm.id.toString()
            title = getfilm.title.toString()
            director = getfilm.director.toString()
            createdAt = getfilm.createdAt.toString()
            synopsis = getfilm.synopsis.toString()
            image = getfilm.image.toString()
        }

        if (getfilmfromfav!=null){
            view.text1.text = getfilmfromfav.title
            view.text2.text = getfilmfromfav.director
            view.text3.text = getfilmfromfav.createdAt
            view.text4.text = getfilmfromfav.synopsis
            Glide.with(requireContext()).load(getfilmfromfav.image).into(view.gambar1)

            id = getfilmfromfav.id.toString()
            title = getfilmfromfav.title
            director = getfilmfromfav.director
            createdAt = getfilmfromfav.createdAt
            synopsis = getfilmfromfav.synopsis
            image = getfilmfromfav.image
        }





        favorite = "false"
        GlobalScope.async {
            film = db?.getFavoriteDao()?.getFilmID(id.toInt())!!

            if (film?.isfav == "true"){
                btnfavorite.setImageResource(R.drawable.love)
                favorite = "true"
            }

            if (film?.isfav == "false"){
                btnfavorite.setImageResource(R.drawable.unlove)
                favorite = "false"
            }
        }

        view.btnfavorite.setOnClickListener {

            for (data in favorite){
                if (favorite == "true" ){
                    btnfavorite.setImageResource(R.drawable.unlove)
                    favorite = "false"
                    GlobalScope.async {
                        db?.getFavoriteDao()?.deleteFav(
                            FavoriteFilm(createdAt,
                                director,
                                id.toInt(),
                                image,
                                "",
                                synopsis,
                                title,
                                "false")
                        )

                    }
                    break
                }

                if (favorite == "false" ){
                    btnfavorite.setImageResource(R.drawable.love)
                    favorite = "true"
                    GlobalScope.async {
                        db?.getFavoriteDao()?.addFilm(
                            FavoriteFilm(
                                createdAt,
                                director,
                                id.toInt(),
                                image,
                                "",
                                synopsis,
                                title,
                                "true"
                            )
                        )
                    }
                    break
                }
            }
        }
        return view
    }

}