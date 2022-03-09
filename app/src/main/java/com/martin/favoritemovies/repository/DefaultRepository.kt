package com.martin.favoritemovies.repository

import androidx.paging.PagingData
import com.martin.favoritemovies.api.models.MovieDetails
import com.martin.favoritemovies.api.models.TopRatedMovies
import com.martin.favoritemovies.util.Resource
import kotlinx.coroutines.flow.Flow

interface DefaultRepository {

    fun getTopMovies(): Flow<PagingData<TopRatedMovies.Result>>

    fun getMovieDetails(movieId: Int): Flow<Resource<MovieDetails>>

}