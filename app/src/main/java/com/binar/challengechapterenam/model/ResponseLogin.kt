package com.binar.challengechapterlima.model

import com.google.gson.annotations.SerializedName

data class ResponseLogin(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("responseuser")
    val responseuser: GetAllUserItem
)
