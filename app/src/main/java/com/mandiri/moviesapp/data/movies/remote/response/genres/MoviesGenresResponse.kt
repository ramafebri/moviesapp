package com.mandiri.moviesapp.data.movies.remote.response.genres

import com.google.gson.annotations.SerializedName

data class MoviesGenresResponse (
    @field:SerializedName("genres")
    val genres: List<GenreItemResponse>? = null,
)

data class GenreItemResponse (
    @field:SerializedName("id")
    val id: Int? = null,
    @field:SerializedName("name")
    val name: String? = null
)