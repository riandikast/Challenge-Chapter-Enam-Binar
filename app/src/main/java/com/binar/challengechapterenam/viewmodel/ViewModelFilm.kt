package com.binar.challengechapterenam.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.binar.challengechapterenam.model.GetAllFilmItem

import com.binar.challengechapterenam.network.ApiClient
import retrofit2.Call
import retrofit2.Response


class ViewModelFilm (): ViewModel() {
    lateinit var liveDataFilm : MutableLiveData<List<GetAllFilmItem>>

    init {
        liveDataFilm = MutableLiveData()
    }

    fun getLiveFilmObserver() : MutableLiveData<List<GetAllFilmItem>> {
        return liveDataFilm
    }


    fun makeFilmApi(){
        ApiClient.instance.getAllFilm()
            .enqueue(object : retrofit2.Callback<List<GetAllFilmItem>>{
                override fun onResponse(
                    call: Call<List<GetAllFilmItem>>,
                    response: Response<List<GetAllFilmItem>>
                ) {
                    if (response.isSuccessful){
                        liveDataFilm.postValue(response.body())

                    }else{
                        liveDataFilm.postValue(null)

                    }
                }

                override fun onFailure(call: Call<List<GetAllFilmItem>>, t: Throwable) {
                    liveDataFilm.postValue(null)
                }
            })
}}

