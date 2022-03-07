package com.martin.favoritemovies.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.martin.favoritemovies.api.MoviesApi
import com.martin.favoritemovies.api.models.TopRatedMovies
import com.martin.favoritemovies.features.main.MoviesPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesApi: MoviesApi,
) : DefaultRepository {


    override fun getTopMovies(refreshOnInit: Boolean
    ): Flow<PagingData<TopRatedMovies.Result>> {
        return Pager(PagingConfig(pageSize = 20)) {
            MoviesPagingSource(moviesApi)
        }.flow
    }


}