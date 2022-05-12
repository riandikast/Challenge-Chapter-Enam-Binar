package com.binar.challengechapterenam.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.binar.challengechapterenam.R
import com.binar.challengechapterenam.database.FavoriteDB
import com.binar.challengechapterenam.database.FavoriteFilm
import com.binar.challengechapterenam.datastore.UserManager
import com.binar.challengechapterenam.viewmodel.ViewModelFilm
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_favorite.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class FavoriteFragment : Fragment() {
    var db: FavoriteDB? = null
    var film : FavoriteFilm? = null
    lateinit var userManager : UserManager
    lateinit var adapterFavorite : AdapterFavorite
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_favorite, container, false)
        db = FavoriteDB.getInstance(requireContext())
        GlobalScope.async {
            film = db?.getFavoriteDao()?.getFilmID(id.toInt())!!
            db?.getFavoriteDao()?.getAllFav()
        }
        userManager = UserManager(requireContext())
        userManager.userUsername.asLiveData().observe(requireActivity()){
            view.welcomefav.text = it.toString()
        }
        view.home.setOnClickListener {
            view.findNavController().navigate(R.id.action_favoriteFragment_to_homeFragment)
        }
        view.profilefav.setOnClickListener {
            view.findNavController().navigate(R.id.action_favoriteFragment_to_profileFragment)
        }

        view.listfav.layoutManager = LinearLayoutManager(requireContext())
        GlobalScope.launch {
        val listdata = db?.getFavoriteDao()?.getAllFav()
        requireActivity().runOnUiThread {
            listdata.let {
                if (listdata?.size == 0) {
                    checkdatafav.text = "Belum ada favorite"
                }
                adapterFavorite = AdapterFavorite(){
                    val bund = Bundle()
                    bund.putParcelable("detailfilmfromfav", it)
                    view.findNavController().navigate(R.id.action_favoriteFragment_to_detailFragment,bund)
                }
                view.listfav.adapter = adapterFavorite
                adapterFavorite.setDataFav(it!!)
            }
        }
        }



        return view
    }






}