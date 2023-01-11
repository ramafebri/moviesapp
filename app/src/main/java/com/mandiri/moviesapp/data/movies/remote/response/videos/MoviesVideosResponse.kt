package com.mandiri.moviesapp.data.movies.remote.response.videos

import com.google.gson.annotations.SerializedName

data class MoviesVideosResponse(
    @field:SerializedName("results")
    val results: List<VideoItemResponse>? = null
)

data class VideoItemResponse(
    @field:SerializedName("site")
    val site: String? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("key")
    val key: String? = null
)
