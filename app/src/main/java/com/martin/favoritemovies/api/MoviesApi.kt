package com.martin.favoritemovies.api

import com.martin.favoritemovies.api.models.TopRatedMovies
import retrofit2.http.GET
import retrofit2.http.Query


interface MoviesApi {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org"
    }

    @GET("/3/movie/top_rated")
    suspend fun getTopMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): TopRatedMovies
}