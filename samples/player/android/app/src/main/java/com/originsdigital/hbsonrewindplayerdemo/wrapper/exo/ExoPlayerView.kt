package com.originsdigital.hbsonrewindplayerdemo.wrapper.exo

import android.content.Context
import android.os.Looper
import android.util.AttributeSet
import android.view.*
import android.widget.FrameLayout
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.util.Assertions
import com.google.android.exoplayer2.video.VideoSize
import com.netcosports.dioptra.exo.AspectRatioFrameLayout

open class ExoPlayerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) :
    FrameLayout(context, attrs, defStyleAttr) {

    private var contentFrame: AspectRatioFrameLayout
    var videoSurfaceView: View?
        private set

    private val componentListener: ComponentListener = ComponentListener()

    var player: ExoPlayer? = null
        set(value) {
            Assertions.checkState(Looper.myLooper() == Looper.getMainLooper())
            Assertions.checkArgument(value == null || value.applicationLooper == Looper.getMainLooper())

            if (field === value) {
                return
            }

            val surface = videoSurfaceView

            val oldPlayer = field
            if (oldPlayer != null) {
                oldPlayer.removeListener(componentListener)
                if (surface is TextureView) {
                    oldPlayer.clearVideoTextureView(surface)
                } else if (surface is SurfaceView) {
                    oldPlayer.clearVideoSurfaceView(surface)
                }
            }

            field = value

            if (value != null) {
                if (surface is TextureView) {
                    value.setVideoTextureView(surface)
                } else if (surface is SurfaceView) {
                    value.setVideoSurfaceView(surface)
                }
                value.addListener(componentListener)
            }
        }

    init {
        val surfaceType = SURFACE_TYPE_TEXTURE_VIEW
        val resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT

        descendantFocusability = ViewGroup.FOCUS_AFTER_DESCENDANTS

        val contentLayoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        contentLayoutParams.gravity = Gravity.CENTER

        contentFrame = AspectRatioFrameLayout(context)
        addView(contentFrame, contentLayoutParams)

        contentFrame.resizeMode = resizeMode

        // Create a surface view and insert it into the content frame, if there is one.
        if (surfaceType != SURFACE_TYPE_NONE) {
            val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
            videoSurfaceView = when (surfaceType) {
                SURFACE_TYPE_TEXTURE_VIEW -> TextureView(context)
                else -> SurfaceView(context)
            }
            videoSurfaceView?.layoutParams = params
            contentFrame.addView(videoSurfaceView, 0)
        } else {
            videoSurfaceView = null
        }
    }

    override fun setVisibility(visibility: Int) {
        super.setVisibility(visibility)
        if (videoSurfaceView is SurfaceView) {
            // Work around https://github.com/google/ExoPlayer/issues/3160.
            videoSurfaceView?.visibility = visibility
        }
    }

    private inner class ComponentListener : Player.Listener {
        override fun onVideoSizeChanged(videoSize: VideoSize) {
            val videoAspectRatio =
                if (videoSize.height == 0 || videoSize.width == 0) 1F else
                    videoSize.width * videoSize.pixelWidthHeightRatio / videoSize.height

            contentFrame.setAspectRatio(videoAspectRatio)
        }
    }

    companion object {
        private const val SURFACE_TYPE_NONE = 0
        private const val SURFACE_TYPE_SURFACE_VIEW = 1
        private const val SURFACE_TYPE_TEXTURE_VIEW = 2
    }
}