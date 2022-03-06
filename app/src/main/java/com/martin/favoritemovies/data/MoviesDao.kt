package com.martin.favoritemovies.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.martin.favoritemovies.data.models.TopRatedMoviesTable
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopRatedMovie(comments: List<TopRatedMoviesTable>)

    @Query("SELECT * FROM top_movies_table")
    fun getTopRatedMovies(): Flow<TopRatedMoviesTable>
}