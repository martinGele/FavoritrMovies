package com.martin.favoritemovies.repository

import com.martin.favoritemovies.data.models.TopRatedMoviesTable
import com.martin.favoritemovies.util.Resource
import kotlinx.coroutines.flow.Flow

interface DefaultRepository {

    fun getTopMovies(): Flow<Resource<TopRatedMoviesTable>>
}