package com.mandiri.moviesapp.ui.video

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.mandiri.moviesapp.BuildConfig
import com.mandiri.moviesapp.databinding.ActivityVideoPlayerBinding

class VideoPlayerActivity : YouTubeBaseActivity() {
    private lateinit var binding: ActivityVideoPlayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initYoutubePlayer(
            intent.getStringExtra(EXTRA_VIDEO_KEY_DATA).orEmpty()
        )
    }

    private fun initYoutubePlayer(videoId: String) {
        binding.youtubePlayerView.initialize(
            BuildConfig.YOUTUBE_API_KEY,
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider,
                    youTubePlayer: YouTubePlayer,
                    boolean: Boolean
                ) {
                    youTubePlayer.apply {
                        loadVideo(videoId)
                        setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
                        setShowFullscreenButton(true)
                        setFullscreen(false)
                        fullscreenControlFlags = YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT
                        setOnFullscreenListener { isFullscreen ->
                            if (isFullscreen) setOrientation(
                                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                            ) else setOrientation(
                                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                            )
                        }
                        setPlayerStateChangeListener(object :
                            YouTubePlayer.PlayerStateChangeListener {
                            override fun onLoading() {
                                // Do nothing
                            }

                            override fun onLoaded(s: String) {
                                // Do nothing
                            }

                            override fun onAdStarted() {
                                // Do nothing
                            }

                            override fun onVideoStarted() {
                                // Do nothing
                            }

                            override fun onVideoEnded() {
                                release()
                                finish()
                            }

                            override fun onError(errorReason: YouTubePlayer.ErrorReason) {
                                // Do nothing
                            }
                        })
                    }
                }

                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider,
                    youTubeInitializationResult: YouTubeInitializationResult
                ) {
                    // Do nothing
                }
            })
    }

    private fun setOrientation(orientationType: Int) {
        requestedOrientation = orientationType
    }

    companion object {
        private const val EXTRA_VIDEO_KEY_DATA = "EXTRA_VIDEO_KEY_DATA"
        fun startActivity(context: Context, key: String) {
            context.startActivity(Intent(context, VideoPlayerActivity::class.java).apply {
                putExtra(EXTRA_VIDEO_KEY_DATA, key)
            })
        }
    }
}