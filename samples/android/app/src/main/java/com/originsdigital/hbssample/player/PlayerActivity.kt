package com.originsdigital.hbssample.player

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
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import com.netcosports.dioptra.core.ScreenMode
import com.netcosports.onrewind.mediacontroller.MediaControllerMode
import com.netcosports.onrewind.ui.OnRewindPlayerFragment
import com.netcosports.onrewind.ui.PlayerParameters
import com.netcosports.onrewind.ui.util.isPortrait
import com.netcosports.onrewind.ui.viewmodel.OnRewindPlayerViewModel
import com.netcosports.onrewind.ui.viewmodel.OnRewindPlayerViewModelFactory
import com.originsdigital.hbslibrary.R

class PlayerActivity : AppCompatActivity() {

    private var vm: OnRewindPlayerViewModel? = null
    private val constraintSetFullscreen = ConstraintSet()
    private val constraintSetNormal = ConstraintSet()

    private var isPlayerInPortrait: Boolean = true

    private val returnToUserOrientationAction = Runnable {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_USER
    }

    private var playerActivityContainer: ConstraintLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isPortrait = resources.configuration.isPortrait()

        setContentView(R.layout.activity_player_normal)
        playerActivityContainer = findViewById(R.id.playerActivityContainer)

        windowInsetController = WindowInsetsControllerCompat(window, playerActivityContainer!!)
        updateFlags()

        constraintSetFullscreen.clone(this, R.layout.activity_player_fullscreen)
        constraintSetNormal.clone(this, R.layout.activity_player_normal)

        if (savedInstanceState == null) {
            showPlayer()
        }

        vm = ViewModelProvider(this, OnRewindPlayerViewModelFactory())
            .get(OnRewindPlayerViewModel::class.java)


        vm?.requestControllerMode(MediaControllerMode.NORMAL)
        updateControllerMode(false)

        updatePlayerConstraints(isPortrait)

        vm?.fullscreenButtonToggledLiveData?.observe(this) {
            if (it != null) {
                updateOrientation(!isPlayerInPortrait)
                vm?.consumeFullscreenEvent()
            }
        }

        vm?.exitEnrichedModeRequestedLiveData?.observe(this) {
            if (it != null) {
                updateControllerMode(false)
                vm?.consumeExitEnrichedModeScreenEvent()
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
        if (vm?.canEnterPip == true) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val topLeftLocation = IntArray(2)
                val fragmentContainer = findViewById<View>(R.id.fragmentContainer)
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
        val videoUrl = intent.getStringExtra(EXTRA_VIDEO_URL).orEmpty()
        val config = PlayerParameters.Builder()
            .setDirectVideoParams(
                PlayerParameters.DirectVideoParams.Builder()
                    .setVideoUrl(videoUrl)
                    .build()
            )
            .build()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, OnRewindPlayerFragment.newInstance(config))
            .commit()
    }

    private fun updateControllerMode(isEnriched: Boolean) {
        val mode = if (isEnriched) MediaControllerMode.ENRICHED else MediaControllerMode.NORMAL
        updateOrientation(mode == MediaControllerMode.NORMAL)

        vm?.requestControllerMode(mode)
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
        vm?.updateScreenMode(if (isPortrait) ScreenMode.NORMAL else ScreenMode.FULLSCREEN)
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
        WindowCompat.setDecorFitsSystemWindows(window, false)
        windowInsetController?.apply {
            show(WindowInsetsCompat.Type.systemBars())
        }
    }

    private fun setLandFlags() {
        WindowCompat.setDecorFitsSystemWindows(window, true)
        windowInsetController?.apply {
            hide(WindowInsetsCompat.Type.systemBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    companion object {
        private const val EXTRA_VIDEO_URL = "extra_video_url"
        fun getLaunchIntent(context: Context, url: String): Intent {
            return Intent(context, PlayerActivity::class.java)
                .putExtra(EXTRA_VIDEO_URL, url)
        }
    }
}