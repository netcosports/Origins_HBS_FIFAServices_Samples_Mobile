package com.react_native.player;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.origins.onrewind.domain.models.player.MediaControllerMode;
import com.origins.onrewind.domain.models.player.ScreenMode;
import com.origins.onrewind.ui.OnRewindPlayerView;
import com.react_native.R;

public class PlayerActivity extends AppCompatActivity {

    private ConstraintSet constraintSetFullscreen = new ConstraintSet();
    private ConstraintSet constraintSetNormal = new ConstraintSet();

    private boolean isPlayerInPortrait = true;


    private Runnable returnToUserOrientationAction = new Runnable() {
        @Override
        public void run() {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);
        }
    };

    private ConstraintLayout playerActivityContainer = null;
    private OnRewindPlayerView playerView = null;

    private boolean isLive() {
        return getIntent().getStringExtra(EXTRA_MATCH_ID) != null;
    }


    private static final String EXTRA_VIDEO_URL = "extra_video_url";
    private static final String EXTRA_MATCH_ID = "extra_match_id";

    public static Intent getLaunchIntent(Context context, String url) {
        return new Intent(context, PlayerActivity.class)
                .putExtra(EXTRA_VIDEO_URL, url);
    }

    public static Intent getLaunchIntentForMatchId(Context context, String matchId) {
        return new Intent(context, PlayerActivity.class)
                .putExtra(EXTRA_MATCH_ID, matchId);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final boolean isPortrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;

        setContentView(R.layout.activity_player_normal);
        playerActivityContainer = findViewById(R.id.playerActivityContainer);

        windowInsetController = new WindowInsetsControllerCompat(getWindow(), playerActivityContainer);
        updateFlags();

        constraintSetFullscreen.clone(this, R.layout.activity_player_fullscreen);
        constraintSetNormal.clone(this, R.layout.activity_player_normal);
    }

    private void updatePlayerConstraints(boolean isPortrait) {
        if (isPlayerInPortrait == isPortrait) return;
        isPlayerInPortrait = isPortrait;
        ConstraintSet set;
        if (isPortrait) {
            set = constraintSetNormal;
        } else {
            set = constraintSetFullscreen;
        }
        set.applyTo(playerActivityContainer);
        if (playerView != null) {
            playerView.requestScreenMode(isPortrait ? ScreenMode.NORMAL : ScreenMode.FULLSCREEN);
        }
        updateControllerMode(!isPortrait && isLive());
    }


    private void updateControllerMode(boolean isEnriched) {
        MediaControllerMode mode;
        if (isEnriched) {
            mode = MediaControllerMode.ENRICHED;
        } else {
            mode = MediaControllerMode.NORMAL;
        }
        updateOrientation(mode == MediaControllerMode.NORMAL);
        if (playerView != null) {
            playerView.requestControllerMode(mode);
        }
    }

    private void updateOrientation(boolean isPortrait) {
        if (playerActivityContainer != null) {
            playerActivityContainer.removeCallbacks(returnToUserOrientationAction);
        }
        if (!isPortrait) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);
            if (playerActivityContainer != null) {
                playerActivityContainer.postDelayed(returnToUserOrientationAction, 2000);
            }
        }
    }


    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateFlags();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateFlags();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        updateFlags();
    }

    private WindowInsetsControllerCompat windowInsetController = null;

    private void updateFlags() {
        if (isPlayerInPortrait) {
            setPortraitFlags();
        } else {
            setLandFlags();
        }
    }

    private void setPortraitFlags() {
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        if (windowInsetController != null) {
            windowInsetController.show(WindowInsetsCompat.Type.systemBars());
        }
    }

    private void setLandFlags() {
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        if (windowInsetController != null) {
            windowInsetController.hide(WindowInsetsCompat.Type.systemBars());
            windowInsetController.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        }
    }
}


/*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showPlayer()

        updatePlayerConstraints(isPortrait)

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
                playerView?.enterPictureInPicture()
            } else {
                playerView?.exitPictureInPicture()
            }
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
        val videoUrl = intent.getStringExtra(EXTRA_VIDEO_URL)
        val matchId = intent.getStringExtra(EXTRA_MATCH_ID)

        val config = PlayerParameters.Builder()
            .setMatchId(matchId)
            .apply {
                if (videoUrl != null) {
                    setDirectVideoParams(
                        PlayerParameters.DirectVideoParams.Builder()
                            .setVideoUrl(videoUrl)
                            .build()
                    )
                }
            }
            .build()

        val player = OnRewindPlayerView(this, config)
        playerView = player

        val container = findViewById<ViewGroup>(R.id.playerContainer)
        container.addView(player,
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        )
    }

}
 */