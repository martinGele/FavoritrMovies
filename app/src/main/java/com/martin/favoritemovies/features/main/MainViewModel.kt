package com.martin.favoritemovies.features.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.martin.favoritemovies.repository.DefaultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    private val repository: DefaultRepository) : ViewModel() {

    fun getTopMovies() = repository.getTopMovies().cachedIn(viewModelScope)
}