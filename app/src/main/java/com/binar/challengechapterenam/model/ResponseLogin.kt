package com.binar.challengechapterenam.model

import com.binar.challengechapterenam.model.GetAllUserItem
import com.google.gson.annotations.SerializedName

data class ResponseLogin(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("responseuser")
    val responseuser: GetAllUserItem
)
