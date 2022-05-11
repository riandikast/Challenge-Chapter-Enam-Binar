package com.binar.challengechapterlima.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseRegister (
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String
    ): Parcelable