package com.mandiri.moviesapp.domain.movies.model.reviews

import com.mandiri.moviesapp.data.movies.remote.response.reviews.AuthorDetailsResponse

data class AuthorDetailsModel(
    val avatarPath: String,
    val username: String
) {
    companion object {
        fun from(response: AuthorDetailsResponse?): AuthorDetailsModel {
            return AuthorDetailsModel(
                response?.avatarPath.orEmpty(),
                response?.username.orEmpty()
            )
        }
    }
}