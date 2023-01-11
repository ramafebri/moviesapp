package com.mandiri.moviesapp.data.movies.remote.response.reviews

import com.google.gson.annotations.SerializedName
import java.util.*

data class MoviesReviewsResponse(
	@field:SerializedName("total_pages")
	val totalPages: Int? = null,
	@field:SerializedName("results")
	val results: List<ReviewItemResponse>? = null
)

data class ReviewItemResponse(
	@field:SerializedName("author_details")
	val authorDetails: AuthorDetailsResponse? = null,

	@field:SerializedName("updated_at")
	val updatedAt: Date? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("content")
	val content: String? = null
)

data class AuthorDetailsResponse(
	@field:SerializedName("avatar_path")
	val avatarPath: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)

