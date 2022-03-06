package com.martin.favoritemovies.repository

import com.martin.favoritemovies.api.MoviesApi
import com.martin.favoritemovies.data.MoviesDao
import com.martin.favoritemovies.data.MoviesDatabase
import com.martin.favoritemovies.data.models.TopRatedMoviesTable
import com.martin.favoritemovies.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesApi: MoviesApi,
    private val moviesDb: MoviesDatabase,
    private val moviesDao: MoviesDao) : DefaultRepository {

    override fun getTopMovies(): Flow<Resource<TopRatedMoviesTable>> {

        return flow {

        }
    }


}