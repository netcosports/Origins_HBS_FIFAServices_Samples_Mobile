package com.originsdigital.hbsonrewindplayerdemo

import android.app.PictureInPictureParams
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.util.Rational
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.origins.dioptra.core.ScreenMode
import com.origins.onrewind.domain.models.player.MediaControllerMode
import com.origins.onrewind.ui.OnRewindPlayerView
import com.origins.onrewind.ui.PlayerParameters
import com.origins.onrewind.ui.util.isPortrait
import com.originsdigital.hbsonrewindplayerdemo.wrapper.exo.ExoPlayerWrapper

class PlayerActivity : AppCompatActivity() {

    private val constraintSetFullscreen = ConstraintSet()
    private val constraintSetNormal = ConstraintSet()

    private var isPlayerInPortrait: Boolean = true

    private val returnToUserOrientationAction = Runnable {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_USER
    }

    private var playerActivityContainer: ConstraintLayout? = null

    private var playerView: OnRewindPlayerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isPortrait = resources.configuration.isPortrait()

        setContentView(R.layout.activity_player_normal)
        playerActivityContainer = findViewById(R.id.playerActivityContainer)

        windowInsetController = WindowInsetsControllerCompat(window, window.decorView)
        updateFlags()

        constraintSetFullscreen.clone(this, R.layout.activity_player_fullscreen)
        constraintSetNormal.clone(this, R.layout.activity_player_normal)

        showPlayer()

        updateControllerMode(false)
        updatePlayerConstraints(isPortrait)

        findViewById<View>(R.id.enterEnrichedMode).setOnClickListener {
            updateControllerMode(true)
        }

        playerView?.fullscreenButtonToggleHandler =
            OnRewindPlayerView.FullscreenButtonToggleHandler {
                updateOrientation(!isPlayerInPortrait)
            }

        playerView?.exitEnrichModeHandler = OnRewindPlayerView.ExitEnrichModeHandler {
            updateControllerMode(false)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            playerView?.onPlayerCanGoPictureInPictureListener =
                OnRewindPlayerView.OnPlayerCanGoPictureInPictureListener { canGoPip ->
                    val topLeftLocation = IntArray(2)
                    val fragmentContainer = findViewById<View>(R.id.playerContainer)
                    fragmentContainer.getLocationInWindow(topLeftLocation)

                    val rect = Rect().apply {
                        left = topLeftLocation[0]
                        top = topLeftLocation[1]
                        right = left + fragmentContainer.width
                        bottom = top + fragmentContainer.height
                    }

                    val params = PictureInPictureParams.Builder()
                        .setAspectRatio(Rational(16, 9))
                        .setSourceRectHint(rect)
                        .setAutoEnterEnabled(canGoPip)
                        .build()

                    setPictureInPictureParams(params)
                }
        }

        addOnPictureInPictureModeChangedListener {
            if (it.isInPictureInPictureMode) {
                playerView?.enterPip()
            } else {
                playerView?.exitPip()
            }
        }
    }

    private fun updateOrientation(isPortrait: Boolean) {
        playerActivityContainer?.removeCallbacks(returnToUserOrientationAction)
        if (!isPortrait) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE
        } else {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT
            playerActivityContainer?.postDelayed(returnToUserOrientationAction, 2000)
        }
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
            if (playerView?.canEnterPictureInPicture == true) {
                val topLeftLocation = IntArray(2)
                val fragmentContainer = findViewById<View>(R.id.playerContainer)
                fragmentContainer.getLocationInWindow(topLeftLocation)

                val rect = Rect().apply {
                    left = topLeftLocation[0]
                    top = topLeftLocation[1]
                    right = left + fragmentContainer.width
                    bottom = top + fragmentContainer.height
                }

                enterPictureInPictureMode(
                    PictureInPictureParams
                        .Builder()
                        .setAspectRatio(Rational(16, 9))
                        .setSourceRectHint(rect)
                        .build()
                )
            }
        }
    }


    private fun showPlayer() {
        val config = PlayerParameters.Builder().apply {
            if (intent.getBooleanExtra(USE_WRAPPER, false)) {
                setWrapper(ExoPlayerWrapper(context = this@PlayerActivity))
            }
        }
            //Test Event params
            .setEventConfigurationUrl("https://storage.googleapis.com/static-production.netcosports.com/onrewind/hbs_demo_player_config.json")
            .setAccountKey("B1oYoKWDK")
            .setIsChromecastEnabled(true)
            .build()

        val player = OnRewindPlayerView(this, config)

        playerView = player
        val playerContainer = findViewById<ViewGroup>(R.id.playerContainer)
        playerContainer.addView(
            player,
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
    }

    private fun updateControllerMode(isEnriched: Boolean) {
        val mode = if (isEnriched) MediaControllerMode.ENRICHED else MediaControllerMode.NORMAL
        updateOrientation(mode == MediaControllerMode.NORMAL)

        playerView?.requestControllerMode(mode)
    }

    private fun updatePlayerConstraints(isPortrait: Boolean) {
        if (isPlayerInPortrait == isPortrait) return
        isPlayerInPortrait = isPortrait
        val set = if (isPortrait) {
            constraintSetNormal
        } else {
            constraintSetFullscreen
        }
        set.applyTo(playerActivityContainer)
        playerView?.requestScreenMode(if (isPortrait) ScreenMode.NORMAL else ScreenMode.FULLSCREEN)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        updatePlayerConstraints(newConfig.isPortrait())
        updateFlags()
    }

    override fun onResume() {
        super.onResume()
        updateFlags()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        updateFlags()
    }

    private var windowInsetController: WindowInsetsControllerCompat? = null


    private fun updateFlags() {
        if (isPlayerInPortrait) {
            setPortraitFlags()
        } else {
            setLandFlags()
        }
    }

    private fun setPortraitFlags() {
        WindowCompat.setDecorFitsSystemWindows(window, true)
        windowInsetController?.apply {
            show(WindowInsetsCompat.Type.systemBars())
        }
    }

    private fun setLandFlags() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        windowInsetController?.apply {
            hide(WindowInsetsCompat.Type.systemBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    companion object {
        private const val USE_WRAPPER = "extra_use_wrapper"

        fun getLaunchIntent(context: Context, useWrapper: Boolean): Intent {
            return Intent(context, PlayerActivity::class.java)
                .putExtra(USE_WRAPPER, useWrapper)
        }
    }
}