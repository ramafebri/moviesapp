package com.mandiri.moviesapp.domain.movies.model.reviews

import com.mandiri.moviesapp.data.movies.remote.response.reviews.MoviesReviewsResponse
import com.mandiri.moviesapp.extension.orZero

data class MoviesReviewsModel(
    val totalPages: Int,
    val results: List<MoviesReviewsItemModel>
) {
    companion object {
        fun from(response: MoviesReviewsResponse): MoviesReviewsModel {
            return MoviesReviewsModel(
                response.totalPages.orZero(),
                results = MoviesReviewsItemModel.from(response.results)
            )
        }
    }
}
