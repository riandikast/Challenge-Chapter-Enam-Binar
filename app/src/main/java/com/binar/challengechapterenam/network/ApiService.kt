package com.binar.challengechapterenam.network

import com.binar.challengechapterenam.model.GetAllFilmItem
import com.binar.challengechapterenam.model.GetAllUserItem
import com.binar.challengechapterenam.model.ResponseLogin
import com.binar.challengechapterenam.model.ResponseRegister
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("apifilm.php")
    fun getAllFilm(): Call<List<GetAllFilmItem>>

    @GET("apiuser.php")
    fun getAllUser(): Call<List<GetAllUserItem>>


    @POST("updateuser.php")
    @FormUrlEncoded
    fun updateUser(
        @Field("id") id: Int,
        @Field("username") username: String,
        @Field("complete_name") completename: String,
        @Field("address") address: String,
        @Field("dateofbirth") dateofbirth: String,
        ): Call<List<GetAllUserItem>>


    @POST("register.php/")
    @FormUrlEncoded
    fun register(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String,

    ): Call<ResponseRegister>

    @POST("login.php")
    @FormUrlEncoded
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponseLogin>


}