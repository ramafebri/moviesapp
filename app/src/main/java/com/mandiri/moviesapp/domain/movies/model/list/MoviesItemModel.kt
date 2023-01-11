package com.mandiri.moviesapp.domain.movies.model.list

import com.mandiri.moviesapp.data.movies.remote.response.list.MoviesItemResponse
import com.mandiri.moviesapp.extension.orZero

data class MoviesItemModel(
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Float,
    val id: Int
) {
    companion object {
        fun from(response: List<MoviesItemResponse>?): List<MoviesItemModel> {
            return response?.map {
                MoviesItemModel(
                    it.title.orEmpty(),
                    it.posterPath.orEmpty(),
                    it.releaseDate.orEmpty(),
                    it.voteAverage.orZero(),
                    it.id.orZero()
                )
            }.orEmpty()
        }
    }
}
