package com.binar.challengechapterenam.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [FavoriteFilm::class],
    version = 4
)
abstract class FavoriteDB : RoomDatabase(){
    abstract fun getFavoriteDao():FavoriteFilmDao

    companion object{
        private var INSTANCE : FavoriteDB? = null

        fun getInstance(context: Context):FavoriteDB?{
            if (INSTANCE == null){
                synchronized(FavoriteDB::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,FavoriteDB::class.java,"Fav.db").build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}