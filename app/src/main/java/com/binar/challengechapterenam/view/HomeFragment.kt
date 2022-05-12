package com.binar.challengechapterenam.view

import android.content.SharedPreferences
import android.os.Bundle
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
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async


class HomeFragment : Fragment() {
    var db: FavoriteDB? = null
    var film : FavoriteFilm? = null
    lateinit var adapterfilm : AdapterFilm
    lateinit var userManager : UserManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)


        view.list.layoutManager = LinearLayoutManager(requireContext())
        adapterfilm = AdapterFilm(){
            val bund = Bundle()
            bund.putParcelable("detailfilm", it)
            view.findNavController().navigate(R.id.action_homeFragment_to_detailFragment,bund)

        }
        view.list.adapter = adapterfilm

        userManager = UserManager(requireContext())
        userManager.userUsername.asLiveData().observe(requireActivity()){
            view.welcome.text = it.toString()
        }

        getFilm()

        view.profile.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }
        view.homelove.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
        }

        GlobalScope.async {
            film = db?.getFavoriteDao()?.getFilmID(id.toInt())!!
            db?.getFavoriteDao()?.getAllFav()
        }
        return view
    }


    fun getFilm(){

        val viewModel = ViewModelProvider(requireActivity()).get(ViewModelFilm::class.java)
        viewModel.getLiveFilmObserver().observe(requireActivity()) {
            if(it != null){
                adapterfilm.setDataFilm(it)
                adapterfilm.notifyDataSetChanged()
            }

        }
        viewModel.makeFilmApi()


    }
}