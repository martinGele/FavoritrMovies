package com.martin.favoritemovies.features.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.martin.favoritemovies.R
import com.martin.favoritemovies.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {


    private val viewModel: MainViewModel by viewModels()
    private lateinit var moviesPagingAdapter: MoviesListPagingAdapter
    private lateinit var currentBinding: MainFragmentBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentBinding = MainFragmentBinding.bind(view)

        moviesPagingAdapter = MoviesListPagingAdapter(
            onItemClick = {

            }
        )
        lifecycleScope.launch {
            viewModel.getTopMovies(true).collect {
                moviesPagingAdapter.submitData(it)
            }
        }
        currentBinding.apply {
            rvMovies.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = moviesPagingAdapter
            }
        }
    }
}