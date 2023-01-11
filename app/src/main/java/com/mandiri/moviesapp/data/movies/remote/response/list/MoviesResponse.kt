package com.mandiri.moviesapp.data.movies.remote.response.list

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
	@field:SerializedName("total_pages")
	val totalPages: Int? = null,
	@field:SerializedName("results")
	val results: List<MoviesItemResponse>? = null
)

data class MoviesItemResponse(
	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null,

	@field:SerializedName("release_date")
	val releaseDate: String? = null,

	@field:SerializedName("vote_average")
	val voteAverage: Float? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
