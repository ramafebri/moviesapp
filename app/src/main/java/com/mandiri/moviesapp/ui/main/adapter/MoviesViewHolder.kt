package com.mandiri.moviesapp.ui.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mandiri.moviesapp.R
import com.mandiri.moviesapp.databinding.ItemMovieBinding
import com.mandiri.moviesapp.domain.movies.model.list.MoviesItemModel
import com.mandiri.moviesapp.extension.getMoviesUrl

class MoviesViewHolder(private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: MoviesItemModel) {
        with(binding) {
            textViewTitle.text = item.title
            textViewDate.text = item.releaseDate
            textViewRating.text = root.context.getString(R.string.rating_text, item.voteAverage.toString())
            with(imageViewMovies) {
                Glide.with(context)
                    .load(getMoviesUrl(item.posterPath))
                    .into(this)
            }
        }
    }
}