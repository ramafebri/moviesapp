package com.mandiri.moviesapp.ui.video

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import com.mandiri.moviesapp.databinding.ActivityVideoBinding
import com.mandiri.moviesapp.domain.movies.model.videos.MoviesVideosItemModel
import com.mandiri.moviesapp.ui.base.BaseActivity
import com.mandiri.moviesapp.ui.video.adapter.VideoAdapter

class VideoActivity : BaseActivity<ActivityVideoBinding>() {

    private lateinit var videoAdapter: VideoAdapter

    override fun getViewBinding(): ActivityVideoBinding {
        return ActivityVideoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showBackButton()
        generateVideoList()
    }

    private fun generateVideoList() {
        val videoList = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableArrayListExtra(EXTRA_VIDEO_DATA, MoviesVideosItemModel::class.java)
        } else {
            intent.getParcelableArrayListExtra(EXTRA_VIDEO_DATA)
        }

        if (videoList.isNullOrEmpty()) return
        videoAdapter = VideoAdapter(videoList)
        binding.rvVideo.adapter = videoAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.rvVideo.adapter = null
    }

    companion object {
        private const val EXTRA_VIDEO_DATA = "EXTRA_VIDEO_DATA"
        fun startActivity(activity: Activity, data: List<MoviesVideosItemModel>) {
            activity.startActivity(Intent(activity, VideoActivity::class.java).apply {
                putParcelableArrayListExtra(EXTRA_VIDEO_DATA, ArrayList(data))
            })
        }
    }
}