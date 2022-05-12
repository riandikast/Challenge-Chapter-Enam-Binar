package com.binar.challengechapterenam.database

import androidx.room.*

@Dao
interface FavoriteFilmDao {
    @Insert
    fun addFilm (favoriteFilm : FavoriteFilm) : Long
    @Delete()
    fun deleteFav(favoriteFilm: FavoriteFilm ):Int
    @Query("SELECT * FROM Fav WHERE Fav.id = :id")
    fun getFilmID(id:Int): FavoriteFilm
    @Query("SELECT *  FROM Fav")
    fun getAllFav(): List<FavoriteFilm>





//    @Query("SELECT count(*) FROM Fav WHERE Fav.id = :id")
//    suspend fun checkFilm(id :String) : List<FavoriteFilm>
//
//    @Query("DELETE FROM Fav WHERE Fav.id = :id")
//    suspend fun removeFilm(id :String)

//    @Query("SELECT *  FROM Fav")
//    fun getAllFavor(): List<FavoriteFilm>
}