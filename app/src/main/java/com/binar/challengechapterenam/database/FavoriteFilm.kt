package com.binar.challengechapterenam.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Fav")
@Parcelize
data class FavoriteFilm(
    @PrimaryKey(autoGenerate = true)
    val idfav: Int?,
    @ColumnInfo(name = "email")
    val email: String?,
    @ColumnInfo(name = "created_at")
    val createdAt: String,
    @ColumnInfo(name = "director")
    val director: String,
    @ColumnInfo(name = "id")
    val id: Int?,
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

