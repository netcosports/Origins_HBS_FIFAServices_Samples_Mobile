package com.originsdigital.hbsonrewindplayerdemo.wrapper.kaltura

import android.content.Context
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.lifecycle.LifecycleOwner
import com.kaltura.playkit.PlayerEvent
import com.kaltura.playkit.PlayerState
import com.kaltura.playkit.providers.ovp.OVPMediaAsset
import com.kaltura.tvplayer.KalturaOvpPlayer
import com.kaltura.tvplayer.OVPMediaOptions
import com.kaltura.tvplayer.PlayerInitOptions
import com.origins.onrewind.domain.models.player.AudioTrack
import com.origins.onrewind.domain.models.player.PlaybackState
import com.origins.onrewind.domain.models.player.Progress
import com.origins.onrewind.domain.models.player.VideoTrack
import com.origins.onrewind.ui.player.wrapper.*


class KalturaWrapper(
    private val context: Context,
) : OnRewindPlayerWrapper {

    private val player: KalturaOvpPlayer

    override val playerView: View

    init {
        val partnerId = 2215841

        KalturaOvpPlayer.initialize(
            context.applicationContext,
            partnerId,
            "https://cdnapisec.kaltura.com",
        )

        val playerInitOptions = PlayerInitOptions(partnerId) //player config/behavior
        playerInitOptions.setAutoPlay(true)

        val player = KalturaOvpPlayer.create(context, playerInitOptions)
        player.setPlayerView(FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT)

        val ovpMediaOptions = buildOvpMediaOptions()
        player.loadMedia(ovpMediaOptions) { mediaOptions, entry, loadError ->
            if (loadError != null) {
                Log.d(TAG, "OVPMedia error  ${loadError.message}")
            } else {
                Log.d(TAG, "OVPMedia onEntryLoadComplete  entry = " + entry.id)
            }
        }
        this.player = player
        addPlayerEventsListener()
        addPlayerStateListener()

        val frame = FrameLayout(context)
        val lp = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT,
        )

        lp.gravity = Gravity.CENTER
        frame.addView(player.playerView, lp)
        playerView = frame
    }

    override val isPlaybackSpeedSupported: Boolean = false
    override var playbackSpeed: Float = 1F

    override fun goLive(listener: OnSeekCompletedListener) {
        player.seekToLiveDefaultPosition()
        listener.onCompleted()
    }

    override fun seek(progress: Long, listener: OnSeekCompletedListener) {
        player.seekTo(progress)
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
        when (state) {
            PlaybackState.PLAYING -> player.play()
            PlaybackState.PAUSED -> player.pause()
        }
    }

    override fun setSource(url: String?, startOffsetMillis: Long?, playWhenReady: Boolean?) {
        TODO("Not yet implemented")
    }

    override fun retry() = Unit
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

    override fun onResume(owner: LifecycleOwner) {
        player.onApplicationResumed()
        playerView.postDelayed(updateProgressAction, 250)
    }

    override fun onPause(owner: LifecycleOwner) {
        player.onApplicationPaused()
        playerView.removeCallbacks(updateProgressAction)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        player.removeListeners(this)
        player.destroy()
    }

    private fun updateProgress() {
        player.progress()?.let { progress ->
            progressListener?.onDurationUpdated(progress.duration)
            progressListener?.onProgressUpdated(progress.progress)
            progressListener?.onBufferUpdated(progress.buffer)
        }
    }

    private fun KalturaOvpPlayer.progress(): Progress? {
        return Progress(
            progress = this.currentPosition,
            duration = this.duration,
            buffer = this.bufferedPosition,
        )
    }

    private fun addPlayerStateListener() {
        player.addListener(this, PlayerEvent.stateChanged) { event ->

            val newState = when (event.newState) {
                PlayerState.IDLE -> com.origins.onrewind.domain.models.player.PlayerState.Idle
                PlayerState.LOADING -> com.origins.onrewind.domain.models.player.PlayerState.Stuck
                PlayerState.BUFFERING -> com.origins.onrewind.domain.models.player.PlayerState.Stuck
                PlayerState.READY -> {
                    val state =
                        if (player.isPlaying) PlaybackState.PLAYING else PlaybackState.PAUSED
                    com.origins.onrewind.domain.models.player.PlayerState.Ready(state)
                }
            }

            playerStateListener?.onNewState(newState)
        }
    }

    private fun addPlayerEventsListener() {
        player.addListener(this, PlayerEvent.playing) { event ->
            playerStateListener?.onNewState(
                com.origins.onrewind.domain.models.player.PlayerState.Ready(PlaybackState.PLAYING)
            )
        }

        player.addListener(this, PlayerEvent.pause) { event ->
            playerStateListener?.onNewState(
                com.origins.onrewind.domain.models.player.PlayerState.Ready(PlaybackState.PAUSED)
            )
        }

        player.addListener(this, PlayerEvent.error) { event ->
            playerStateListener?.onNewState(
                com.origins.onrewind.domain.models.player.PlayerState.Error()
            )
        }
    }

    private fun buildOvpMediaOptions(): OVPMediaOptions {
        val ovpMediaAsset = OVPMediaAsset()
        ovpMediaAsset.entryId = EntryId
        ovpMediaAsset.ks = null
        return OVPMediaOptions(ovpMediaAsset)
    }

    companion object {
        const val TAG = "KalturaWrapper"
        const val EntryId = "1_w9zx2eti"
    }
}