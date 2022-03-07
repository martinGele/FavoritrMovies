package com.martin.favoritemovies.features.main

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.martin.favoritemovies.BuildConfig
import com.martin.favoritemovies.api.MoviesApi
import com.martin.favoritemovies.api.models.TopRatedMovies

class MoviesPagingSource(private val moviesApi: MoviesApi) : PagingSource<Int, TopRatedMovies.Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TopRatedMovies.Result> {
        return try {
            val currentLoadingPageKey = params.key ?: 1
            val response = moviesApi.getTopMovies(apiKey = BuildConfig.MOVIES_ACCESS_KEY, language = "en", page = currentLoadingPageKey)

            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

            LoadResult.Page(
                data = response.results,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TopRatedMovies.Result>): Int? {
        TODO("Not yet implemented")
    }


}