package com.martin.favoritemovies.repository

import androidx.paging.PagingData
import com.martin.favoritemovies.api.models.TopRatedMovies
import kotlinx.coroutines.flow.Flow

interface DefaultRepository {

    fun getTopMovies(): Flow<PagingData<TopRatedMovies.Result>>
}