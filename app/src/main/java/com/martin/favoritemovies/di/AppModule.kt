package com.martin.favoritemovies.di

import android.app.Application
import androidx.room.Room
import com.martin.favoritemovies.api.MoviesApi
import com.martin.favoritemovies.data.MoviesDao
import com.martin.favoritemovies.data.MoviesDatabase
import com.martin.favoritemovies.repository.DefaultRepository
import com.martin.favoritemovies.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(MoviesApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideMoviesApi(retrofit: Retrofit): MoviesApi =
        retrofit.create(MoviesApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application): MoviesDatabase =
        Room.databaseBuilder(app, MoviesDatabase::class.java, "movies_database")
            .fallbackToDestructiveMigration()
            .build()


    @Singleton
    @Provides
    fun provideDefaultRepository(
        dao: MoviesDao,
        api: MoviesApi,
        moviesDb: MoviesDatabase
    ) = MoviesRepository(api, moviesDb, dao) as DefaultRepository

    @Singleton
    @Provides
    fun provideMoviesDao(
        database: MoviesDatabase
    ) = database.moviesDao()

}