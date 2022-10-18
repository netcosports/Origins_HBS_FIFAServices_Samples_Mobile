package com.originsdigital.hbsonrewindplayerdemo.wrapper.exo

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LifecycleOwner
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.util.Util
import com.origins.onrewind.domain.models.player.*
import com.origins.onrewind.ui.player.core.Source
import com.origins.onrewind.ui.player.wrapper.*
import kotlin.math.max
import kotlin.math.min

class ExoPlayerWrapper(val context: Context) : OnRewindPlayerWrapper {
    private var exoPlayer: ExoPlayer? = null
    private val dataSourceFactory: DataSource.Factory = DefaultHttpDataSource.Factory()
        .setUserAgent(Util.getUserAgent(context, "OnrewindSampleExo"))

    private var startAutoPlay: Boolean = true
    private var startWindow: Int = C.INDEX_UNSET
    private var startPosition: Long = C.TIME_UNSET

    private var playbackParameters: PlaybackParameters = PlaybackParameters.DEFAULT

    override val playerView: ExoPlayerView = ExoPlayerView(context)
    override fun setSource(source: Source<String?>) {
    }

    override val isPlaybackSpeedSupported: Boolean = true
    override var playbackSpeed: Float = 1F
        set(value) {
            field = value
            exoPlayer?.playbackParameters?.let {
                exoPlayer?.playbackParameters = it.withSpeed(field)
            }
        }

    override fun goLive(listener: OnSeekCompletedListener) {
        exoPlayer?.let { player ->
            player.seekToDefaultPosition()
            updateProgress()
        }

        listener.onCompleted()
    }

    override fun retry() = Unit

    override fun seek(progress: Long, listener: OnSeekCompletedListener) {
        exoPlayer?.let { player ->
            player.seekTo(progress)
            updateProgress()
        }

        listener.onCompleted()
    }

    private var playerStateListener: OnPlayerStateListener? = null
    override fun setOnPlayerStateListener(listener: OnPlayerStateListener?) {
        playerStateListener = listener
    }

    private var progressListener: OnProgressUpdateListener? = null
    override fun setOnProgressUpdateListener(listener: OnProgressUpdateListener?) {
        progressListener = listener
    }

    override fun setPlaybackState(state: PlaybackState) {
        when {
            exoPlayer?.playbackState == Player.STATE_IDLE -> initializePlayer()
            exoPlayer?.playbackState == Player.STATE_ENDED -> exoPlayer?.seekTo(0)
            else -> when (state) {
                PlaybackState.PLAYING -> exoPlayer?.playWhenReady = true
                PlaybackState.PAUSED -> {
                    exoPlayer?.playWhenReady = false
                    updateStartPosition()
                }
            }
        }
    }



    override fun selectAudioTrack(audioTrack: AudioTrack) = Unit
    override fun selectVideoTrack(videoTrack: VideoTrack) = Unit
    override fun setOnAudioTracksListener(listener: OnAudioTracksListener?) = Unit
    override fun setOnVideoTracksListener(listener: OnVideoTracksListener?) = Unit

    private val updateProgressAction: Runnable = object : Runnable {
        override fun run() {
            updateProgress()
            playerView.postDelayed(this, 250)
        }
    }

    override fun onStart(owner: LifecycleOwner) {
        initializePlayer()
        playerView.postDelayed(updateProgressAction, 250)
    }

    override fun onStop(owner: LifecycleOwner) {
        releasePlayer()
        playerView.removeCallbacks(updateProgressAction)
    }

    private fun initializePlayer() {
        if (exoPlayer == null) {
            val renderersFactory = DefaultRenderersFactory(playerView.context)

            exoPlayer = ExoPlayer.Builder(context)
                .setRenderersFactory(renderersFactory)
                .build()

            exoPlayer?.addListener(PlayerEventListener())
            exoPlayer?.playWhenReady = startAutoPlay
            exoPlayer?.playbackParameters = playbackParameters

            val audioAttrs = AudioAttributes.Builder()
                .setUsage(C.USAGE_MEDIA)
                .setContentType(C.AUDIO_CONTENT_TYPE_MOVIE)
                .build()

            exoPlayer?.setAudioAttributes(audioAttrs, true)

            playerView.player = exoPlayer
        }

        val stream: Uri =
            Uri.parse("http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8")

        val source = Utils.buildMediaSource(stream, dataSourceFactory)
        val haveStartPosition = startPosition != C.TIME_UNSET
        if (haveStartPosition) {
            val haveStartWindow = startWindow != C.INDEX_UNSET
            if (haveStartWindow) {
                exoPlayer?.seekTo(startWindow, startPosition)
            } else {
                exoPlayer?.seekTo(startPosition)
            }
        }

        exoPlayer?.setMediaSource(source, !haveStartPosition)
        exoPlayer?.prepare()
    }

    private fun releasePlayer() {
        updateStartPosition()
        playbackParameters = exoPlayer?.playbackParameters ?: PlaybackParameters.DEFAULT
        exoPlayer?.release()
        exoPlayer = null
    }

    private fun clearStartPosition() {
        startWindow = C.INDEX_UNSET
        startPosition = C.TIME_UNSET
    }

    private fun updateStartPosition() {
        exoPlayer?.let {
            startWindow = it.currentMediaItemIndex
            startPosition = max(0, it.contentPosition)
        }
    }

    private fun updateProgress() {
        exoPlayer?.progress()?.let { progress ->
            progressListener?.onDurationUpdated(progress.duration)
            progressListener?.onProgressUpdated(progress.progress)
            progressListener?.onBufferUpdated(progress.buffer)
        }
    }

    private fun ExoPlayer.progress(): Progress? {
        val duration = duration

        return if (duration != C.TIME_UNSET) {
            val currentProgress = min(max(contentPosition, 0L), duration)
            val buffer = bufferedPosition
            Progress(currentProgress, buffer, duration)
        } else {
            null
        }
    }

    private inner class PlayerEventListener : Player.Listener {
        override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
            startAutoPlay = playWhenReady

            val state = when (playbackState) {
                Player.STATE_BUFFERING -> if (playWhenReady) PlayerState.Stuck else PlayerState.Buffering
                Player.STATE_ENDED -> PlayerState.Ended
                Player.STATE_IDLE -> PlayerState.Idle
                Player.STATE_READY -> {
                    val state = if (playWhenReady) PlaybackState.PLAYING else PlaybackState.PAUSED
                    PlayerState.Ready(state)
                }
                else -> PlayerState.Idle
            }

            playerStateListener?.onNewState(state)
        }

        override fun onPositionDiscontinuity(@Player.DiscontinuityReason reason: Int) {
            if (exoPlayer?.playerError != null) {
                // The user has performed a seek whilst in the error state. Update the resume position so
                // that if the user then retries, playback resumes from the position to which they seeked.
                updateStartPosition()
            }
        }

        override fun onPlayerError(error: PlaybackException) {
            if (Utils.isBehindLiveWindow(error)) {
                clearStartPosition()
                initializePlayer()
            } else {
                updateStartPosition()
            }

            playerStateListener?.onNewState(PlayerState.Error(error.message))
        }
    }
}

