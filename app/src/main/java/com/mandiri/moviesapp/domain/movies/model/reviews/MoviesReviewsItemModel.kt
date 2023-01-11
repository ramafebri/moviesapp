package com.mandiri.moviesapp.domain.movies.model.reviews

import com.mandiri.moviesapp.data.movies.remote.response.reviews.ReviewItemResponse
import java.util.*

data class MoviesReviewsItemModel(
    val authorDetails: AuthorDetailsModel,
    val updatedAt: Date?,
    val id: String,
    val content: String
) {
    companion object {
        fun from(response: List<ReviewItemResponse>?): List<MoviesReviewsItemModel> {
            return response?.map {
                MoviesReviewsItemModel(
                    authorDetails = AuthorDetailsModel.from(it.authorDetails),
                    it.updatedAt,
                    it.id.orEmpty(),
                    it.content.orEmpty()
                )
            }.orEmpty()
        }
    }
}
