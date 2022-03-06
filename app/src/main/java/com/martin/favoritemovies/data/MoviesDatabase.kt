package com.martin.favoritemovies.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.martin.favoritemovies.data.models.TopRatedMoviesTable

@Database(entities = [TopRatedMoviesTable::class],
    version = 1)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao
}