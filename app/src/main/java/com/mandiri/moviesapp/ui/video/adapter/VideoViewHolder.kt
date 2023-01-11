package com.mandiri.moviesapp.ui.video.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mandiri.moviesapp.databinding.ItemVideoBinding
import com.mandiri.moviesapp.domain.movies.model.videos.MoviesVideosItemModel
import com.mandiri.moviesapp.extension.getYoutubeThumbnailUrl
import com.mandiri.moviesapp.ui.video.VideoPlayerActivity

class VideoViewHolder(private val binding: ItemVideoBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(data: MoviesVideosItemModel) {
        with(binding) {
            imageViewPlay.setOnClickListener {
                VideoPlayerActivity.startActivity(root.context, data.key)
            }
            with(youtubeThumbnailView) {
                Glide.with(context)
                    .load(getYoutubeThumbnailUrl(data.key))
                    .into(this)
            }
        }
    }
}