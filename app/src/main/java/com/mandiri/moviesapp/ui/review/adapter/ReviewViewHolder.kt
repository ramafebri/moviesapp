package com.mandiri.moviesapp.ui.review.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mandiri.moviesapp.common.DateHelper
import com.mandiri.moviesapp.databinding.ItemReviewBinding
import com.mandiri.moviesapp.domain.movies.model.reviews.MoviesReviewsItemModel
import com.mandiri.moviesapp.extension.getMoviesUrl

class ReviewViewHolder(private val binding: ItemReviewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: MoviesReviewsItemModel) {
        with(binding) {
            textViewName.text = item.authorDetails.username
            textViewDate.text = DateHelper.dateParseToString(item.updatedAt, DateHelper.DATE_TIME_FORMAT_DISPLAY)
            textViewReview.text = item.content
            with(imageViewPhoto) {
                Glide.with(context)
                    .load(getMoviesUrl(item.authorDetails.avatarPath))
                    .into(this)
            }
        }
    }
}