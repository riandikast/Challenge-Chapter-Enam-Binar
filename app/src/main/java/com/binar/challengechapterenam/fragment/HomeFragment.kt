package com.binar.challengechapterenam.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.binar.challengechapterenam.R
import com.binar.challengechapterenam.datastore.UserManager

import com.binar.challengechapterlima.adapter.AdapterFilm

import com.binar.challengechapterlima.model.GetAllUserItem
import com.binar.challengechapterlima.viewmodel.ViewModelFilm
import com.binar.challengechapterlima.viewmodel.ViewModelUser
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {

    lateinit var home: SharedPreferences
    lateinit var adapterfilm : AdapterFilm
    lateinit var userManager : UserManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        home = requireContext().getSharedPreferences("login", Context.MODE_PRIVATE)

        view.list.layoutManager = LinearLayoutManager(requireContext())
        adapterfilm = AdapterFilm(){
            val bund = Bundle()
            bund.putParcelable("detailfilm", it)
            view.findNavController().navigate(R.id.action_homeFragment_to_detailFragment,bund)

        }
        view.list.adapter = adapterfilm

        userManager = UserManager(requireContext())
        userManager.userUsername.asLiveData().observe(requireActivity()){
            welcome.text = it.toString()
        }

        getFilm()

        view.profile.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }
        view.homelove.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
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