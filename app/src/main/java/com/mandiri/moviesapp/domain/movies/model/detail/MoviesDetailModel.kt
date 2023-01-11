package com.mandiri.moviesapp.domain.movies.model.detail

import com.mandiri.moviesapp.data.movies.remote.response.detail.MoviesDetailResponse
import com.mandiri.moviesapp.domain.movies.model.genres.MoviesGenresItemModel
import com.mandiri.moviesapp.extension.orFalse
import com.mandiri.moviesapp.extension.orZero

data class MoviesDetailModel(
    val title: String,
    val genres: List<MoviesGenresItemModel>,
    val id: Int,
    val overview: String,
    val runtime: Int,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Float,
    val adult: Boolean,
    val homepage: String
) {
    companion object {
        fun from(response: MoviesDetailResponse): MoviesDetailModel {
            return MoviesDetailModel(
                response.title.orEmpty(),
                genres = MoviesGenresItemModel.from(response.genres),
                response.id.orZero(),
                response.overview.orEmpty(),
                response.runtime.orZero(),
                response.posterPath.orEmpty(),
                response.releaseDate.orEmpty(),
                response.voteAverage.orZero(),
                response.adult.orFalse(),
                response.homepage.orEmpty()
            )
        }
    }
}