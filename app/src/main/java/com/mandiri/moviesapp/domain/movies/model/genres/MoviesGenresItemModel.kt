package com.mandiri.moviesapp.domain.movies.model.genres

import android.os.Parcelable
import com.mandiri.moviesapp.data.movies.remote.response.genres.GenreItemResponse
import com.mandiri.moviesapp.extension.orZero
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesGenresItemModel(
    val id: Int,
    val name: String
) : Parcelable {
    companion object {
        fun from(response: List<GenreItemResponse>?): List<MoviesGenresItemModel> {
            return response?.map {
                MoviesGenresItemModel(
                    it.id.orZero(),
                    it.name.orEmpty()
                )
            }.orEmpty()
        }
    }
}
