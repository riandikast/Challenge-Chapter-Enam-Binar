package com.binar.challengechapterenam.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData

import com.binar.challengechapterenam.R
import com.binar.challengechapterenam.database.FavoriteDB
import com.binar.challengechapterenam.database.FavoriteFilm
import com.binar.challengechapterenam.model.GetAllFilmItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.concurrent.thread
import kotlin.properties.Delegates


class DetailFragment : Fragment() {

    var db: FavoriteDB? = null
    var film : List<FavoriteFilm>? = null

    lateinit var id : String
    lateinit var title : String
    lateinit var director : String
    lateinit var createdAt : String
    lateinit var releaseDate : String
    lateinit var synopsis : String
    lateinit var image: String
    lateinit var email : String
    lateinit var userManager : com.binar.challengechapterenam.datastore.UserManager

    lateinit var toogleFavorite : String
    var alreadyFavorite by Delegates.notNull<Boolean>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        userManager = com.binar.challengechapterenam.datastore.UserManager(requireContext())
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
            releaseDate = getfilm.releaseDate

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
            releaseDate = getfilmfromfav.releaseDate
        }

        email = ""
        toogleFavorite = "false"
        alreadyFavorite = false

        userManager.userEmail.asLiveData().observe(requireActivity()){
            email = it
            GlobalScope.async {
                film = db?.getFavoriteDao()?.checkFav(email, id.toInt())
                val checkDB = film?.size !=0
                    if (checkDB){
                        view.btnfavorite.setImageResource(R.drawable.love)
                        toogleFavorite = "true"
                        alreadyFavorite = true
                                                                                                                                              //codebyandika
                    }else {
                        view.btnfavorite.setImageResource(R.drawable.unlove )
                        toogleFavorite = "false"
                        alreadyFavorite = false
                    }
            }
            }


        view.btnfavorite.setOnClickListener {
            toogleButton()
        }

        return view
    }

    fun toogleButton(){
        for (data in toogleFavorite){
            if (toogleFavorite == "true"  ){
                btnfavorite.setImageResource(R.drawable.unlove)
                toogleFavorite = "false"
                GlobalScope.async {
                    db?.getFavoriteDao()?.deleteFilmID(email, id.toInt())
                }
                break
            }

            if (toogleFavorite == "false"  ) {
                btnfavorite.setImageResource(R.drawable.love)
                toogleFavorite= "true"
                GlobalScope.async {
                    db?.getFavoriteDao()?.addFilm(
                        FavoriteFilm(
                            null,
                            email,
                            createdAt,
                            director,
                            id.toInt(),
                            image,
                            releaseDate,
                            synopsis,
                            title,
                            "true"
                            //codebyandika
                        )
                    )
                }
                break
            }
        }
    }
}