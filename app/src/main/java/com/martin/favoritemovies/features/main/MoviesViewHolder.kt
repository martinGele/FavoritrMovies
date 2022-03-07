package com.martin.favoritemovies.features.main

import androidx.recyclerview.widget.RecyclerView
import com.martin.favoritemovies.api.models.TopRatedMovies
import com.martin.favoritemovies.databinding.ItemMoviesBinding

class MoviesViewHolder(private val binding: ItemMoviesBinding, private val onItemClick: (Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {


    fun bind(currentItem: TopRatedMovies.Result) {

        binding.apply {
            tvTitle.text = currentItem.overview

            root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(position)
                }
            }
        }
    }
}