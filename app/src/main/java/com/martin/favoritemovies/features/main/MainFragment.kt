package com.martin.favoritemovies.features.main

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.martin.favoritemovies.R
import com.martin.favoritemovies.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {


    //instantiate the view model with hilt injection
    private val viewModel: MainViewModel by viewModels()

    //get the movies paging adapter
    private lateinit var moviesPagingAdapter: MoviesListPagingAdapter

    //get the MainFragmentBinding
    private lateinit var currentBinding: MainFragmentBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentBinding = MainFragmentBinding.bind(view)

        //on item click from the view holder pass the pojo object
        moviesPagingAdapter = MoviesListPagingAdapter(
            onItemClick = {


            }
        )

        currentBinding.apply {
            rvMovies.apply {
                layoutManager = LinearLayoutManager(activity)
                // set the footer to the MoviesPagingAdapter
                adapter = moviesPagingAdapter
                    .withLoadStateFooter(
                        MoviesLoadStateAdapter(moviesPagingAdapter::retry))
                setHasFixedSize(true)
            }

            //get top movies on Lifecycle.State.CREATED of fragment
            lifecycleScope.launchWhenCreated {
                viewModel.getTopMovies().collect {
                    moviesPagingAdapter.submitData(it)
                }
            }

            lifecycleScope.launchWhenStarted {
                moviesPagingAdapter.loadStateFlow
                    .collect { loadStates ->

                        pbMain.isVisible = loadStates.refresh is LoadState.Loading
                        bnRetryMain.isVisible = loadStates.refresh is LoadState.Error
                        tvErrorMain.isVisible = loadStates.refresh is LoadState.Error
                    }
            }
            bnRetryMain.setOnClickListener {
                moviesPagingAdapter.retry()
            }
        }
    }
}