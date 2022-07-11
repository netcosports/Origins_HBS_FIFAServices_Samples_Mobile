package com.originsdigital.hbsonrewindplayerdemo.wrapper.dm

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.format.DateUtils
import android.util.AttributeSet
import android.util.Log
import com.dailymotion.android.player.sdk.PlayerWebView
import com.dailymotion.android.player.sdk.events.PlayerEvent
import com.netcosports.dioptra.core.*
import com.netcosports.dioptra.wrapper.*
import com.netcosports.onrewind.ui.player.OnRewindPlayerWrapper

class DmPlayerWrapper(context: Context, bundle: Bundle) : OnRewindPlayerWrapper {
    override val playerView = DmPlayerView2(context)

    init {
        playerView.setPlayerEventListener(::onDmEventReceived)
        playerView.load("x8blk42")
    }

    override var isMuted: Boolean = false
        set(value) {
            if (field != value) {
                field = value
                if (field) {
                    playerView.mute()
                } else {
                    playerView.unmute()
                }
            }
        }

    override val isPlaybackSpeedSupported: Boolean = false
    override fun setOnVideoTracksListener(listener: OnVideoTracksListener?) = Unit
    override fun selectVideoTrack(videoTrack: VideoTrack) = Unit
    override fun setOnAudioTracksListener(listener: OnAudioTracksListener?) = Unit
    override fun selectAudioTrack(audioTrack: AudioTrack) = Unit

    @Suppress("UNUSED_PARAMETER")
    override var playbackSpeed: Float = 1F
        set(value) {
            //DM isn't supported it
            field = 1F
        }

    private var onSeekCompletedListener: OnSeekCompletedListener? = null

    override fun seek(progress: Long, listener: OnSeekCompletedListener) {
        this.onSeekCompletedListener = listener
        playerView.seek(progress.toDouble() / DateUtils.SECOND_IN_MILLIS)
    }

    override fun goLive(listener: OnSeekCompletedListener) {
        this.onSeekCompletedListener = listener
        playerView.seek(playerView.duration)
    }

    override fun setPlaybackState(state: PlaybackState) {
        when (state) {
            PlaybackState.PLAYING -> playerView.play()
            PlaybackState.PAUSED -> playerView.pause()
        }
    }

    private var onProgressUpdateListener: OnProgressUpdateListener? = null
    override fun setOnProgressUpdateListener(listener: OnProgressUpdateListener?) {
        onProgressUpdateListener = listener
    }

    private var onPlayerStateListener: OnPlayerStateListener? = null
    override fun setOnPlayerStateListener(listener: OnPlayerStateListener?) {
        onPlayerStateListener = listener
    }

    override fun onResume() {
        playerView.onResume()
    }

    override fun onPause() {
        playerView.onPause()
    }

    override fun onDestroy() {
        playerView.destroy()
    }

    override fun retry() {
    }

    private fun onDmEventReceived(event: PlayerEvent) {
        Log.i("DmPlayerPresenter", "event ${event.name} params ${event.payload}")
        when (event.name) {
            PlayerWebView.EVENT_APIREADY,
            PlayerWebView.EVENT_START,
            -> onPlayerStateListener?.onNewState(PlayerState.PREPARING)

            PlayerWebView.EVENT_PLAYING -> onPlayerStateListener?.onNewState(
                PlayerState.READY(
                    PlaybackState.PLAYING
                )
            )

            PlayerWebView.EVENT_PAUSE -> onPlayerStateListener?.onNewState(
                PlayerState.READY(
                    PlaybackState.PAUSED
                )
            )

            PlayerWebView.EVENT_DURATION_CHANGE -> {
                val d = (playerView.duration * DateUtils.SECOND_IN_MILLIS).toLong()
                onProgressUpdateListener?.onDurationUpdated(d)
            }

            PlayerWebView.EVENT_TIMEUPDATE -> {
                onProgressUpdateListener?.onProgressUpdated(playerView.position)
            }

            PlayerWebView.EVENT_PROGRESS -> {
                val buffedTime = (playerView.bufferedTime * DateUtils.SECOND_IN_MILLIS).toLong()
                onProgressUpdateListener?.onBufferUpdated(buffedTime)
            }

            PlayerWebView.EVENT_SEEKED -> {
                onSeekCompletedListener?.onCompleted()
                onProgressUpdateListener?.onProgressUpdated(playerView.position)
            }

            PlayerWebView.EVENT_END -> {
                onPlayerStateListener?.onNewState(PlayerState.ENDED)
            }

            PlayerWebView.EVENT_AD_START -> {
                onPlayerStateListener?.onNewState(PlayerState.AD(AdState.PREPARING))
            }

            PlayerWebView.EVENT_AD_PLAY -> {
                onPlayerStateListener?.onNewState(PlayerState.AD(AdState.READY))
            }

            PlayerWebView.EVENT_AD_END -> {
                onPlayerStateListener?.onNewState(PlayerState.AD(AdState.ENDED))
            }

            "_generic_" -> {
                val params = event.payload.split("&".toRegex()).dropLastWhile { it.isEmpty() }

                var eventName: String? = null
                for (param in params) {
                    val splittedParam = param.split("=".toRegex()).dropLastWhile { it.isEmpty() }
                    if (splittedParam.size == 2 && splittedParam[0] == "event") {
                        eventName = splittedParam[1]
                        break
                    }
                }
                Log.i("DmPlayerPresenter", "event $eventName")
                when (eventName) {
                    "waiting" -> onPlayerStateListener?.onNewState(PlayerState.STUCK)
                    "error" -> {
                        onPlayerStateListener?.onNewState(PlayerState.ERROR())
                    }
                }
            }
        }
    }

    class DmPlayerView2 @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
    ) : PlayerWebView(context, attrs, defStyleAttr) {

        init {
            showControls(false)
            setBackgroundColor(Color.TRANSPARENT)
        }

        fun load(id: String, startTime: Double) {
            load(id, mapOf(EVENT_START to startTime))
        }
    }
}