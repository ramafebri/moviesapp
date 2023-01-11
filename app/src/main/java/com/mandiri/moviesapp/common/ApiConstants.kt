package com.mandiri.moviesapp.common

object ApiConstants {
    private const val DISCOVER = "discover"
    private const val GENRE = "genre"
    private const val LIST = "list"
    private const val MOVIE = "movie"
    private const val SLASH = "/"
    private const val REVIEWS = "reviews"
    private const val VIDEOS = "videos"
    const val PAGE = "page"
    const val DEFAULT_PAGE = 1
    const val WITH_GENRES = "with_genres"
    const val MOVIE_ID = "movieId"

    const val GET_MOVIES_GENRES_URL = GENRE + SLASH + MOVIE + SLASH + LIST
    const val GET_MOVIES_URL = DISCOVER + SLASH + MOVIE
    const val GET_MOVIES_DETAIL_URL = "$MOVIE$SLASH{$MOVIE_ID}"
    const val GET_MOVIES_REVIEWS_URL = "$MOVIE$SLASH{$MOVIE_ID}$SLASH$REVIEWS"
    const val GET_MOVIES_VIDEOS_URL = "$MOVIE$SLASH{$MOVIE_ID}$SLASH$VIDEOS"
}