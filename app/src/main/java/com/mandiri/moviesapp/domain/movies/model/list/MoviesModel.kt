package com.mandiri.moviesapp.domain.movies.model.list

import com.mandiri.moviesapp.data.movies.remote.response.list.MoviesResponse
import com.mandiri.moviesapp.extension.orZero

data class MoviesModel(
    val totalPages: Int,
    val results: List<MoviesItemModel>,
) {
    companion object {
        fun from(response: MoviesResponse): MoviesModel {
            return MoviesModel(
                response.totalPages.orZero(),
                results = MoviesItemModel.from(response.results)
            )
        }
    }
}
