package com.binar.challengechapterenam.database

import androidx.room.*

@Dao
interface FavoriteFilmDao {
    @Insert
    fun addFilm (favoriteFilm : FavoriteFilm) : Long
    @Delete
    fun deleteFav(favoriteFilm: FavoriteFilm ):Int
    @Query("SELECT * FROM Fav WHERE Fav.email = :email AND Fav.id = :id ")
    fun checkFav(email: String, id: Int): List<FavoriteFilm>
    @Query("SELECT * FROM Fav WHERE Fav.email = :email ")
    fun getFav(email: String): List<FavoriteFilm>
    @Query("DELETE FROM Fav WHERE Fav.email = :email AND Fav.id = :id")
    fun deleteFilmID(email :String, id:Int): Int
    @Query("SELECT *  FROM Fav")
    fun getAllFav(): List<FavoriteFilm>

}