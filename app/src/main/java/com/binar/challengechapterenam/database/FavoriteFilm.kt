package com.binar.challengechapterenam.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Entity(tableName = "Fav")
@Parcelize
data class FavoriteFilm (
    @ColumnInfo(name = "created_at")
    val createdAt: String,
    @ColumnInfo(name = "director")
    val director: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "releasedate")
    val releaseDate: String,
    @ColumnInfo(name = "synopsis")
    val synopsis: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "status")
    val isfav: String
):Parcelable

