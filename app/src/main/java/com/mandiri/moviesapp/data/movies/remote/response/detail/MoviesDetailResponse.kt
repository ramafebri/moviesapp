package com.mandiri.moviesapp.data.movies.remote.response.detail

import com.google.gson.annotations.SerializedName
import com.mandiri.moviesapp.data.movies.remote.response.genres.GenreItemResponse

data class MoviesDetailResponse(

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("genres")
    val genres: List<GenreItemResponse>? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("runtime")
    val runtime: Int? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("release_date")
    val releaseDate: String? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Float? = null,

    @field:SerializedName("adult")
    val adult: Boolean? = null,

    @field:SerializedName("homepage")
    val homepage: String? = null
)