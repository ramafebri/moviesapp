package com.mandiri.moviesapp.domain.movies.model.videos

import android.os.Parcelable
import com.mandiri.moviesapp.data.movies.remote.response.videos.VideoItemResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesVideosItemModel(
    val site: String,
    val type: String,
    val key: String
) : Parcelable {
    companion object {
        private const val YOUTUBE = "youtube"
        private const val TRAILER = "trailer"
        fun from(response: List<VideoItemResponse>?): List<MoviesVideosItemModel> {
            return response?.filter {
                it.site.equals(YOUTUBE, true) && it.type.equals(
                    TRAILER,
                    true
                )
            }?.map {
                MoviesVideosItemModel(
                    it.site.orEmpty(),
                    it.type.orEmpty(),
                    it.key.orEmpty()
                )
            }.orEmpty()
        }
    }
}
