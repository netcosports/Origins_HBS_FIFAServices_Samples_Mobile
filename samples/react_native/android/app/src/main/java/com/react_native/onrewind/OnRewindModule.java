package com.react_native.onrewind;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.react_native.player.PlayerActivity;

public class OnRewindModule extends ReactContextBaseJavaModule {

    public static final String REACT_CLASS = "OnRewind";

    private final Context context;

    OnRewindModule(ReactApplicationContext context) {
        super(context);
        this.context = context;
    }

    @ReactMethod
    public void presentPlayer(String matchId, String streamUrl) {
        Activity activity = getCurrentActivity();
        if (activity == null) {
            return;
        }
        if (!streamUrl.isEmpty()) {
            Log.d("tttt", "video url: " + streamUrl);
            activity.startActivity(PlayerActivity.getLaunchIntent(getCurrentActivity(), streamUrl));
        } else if (!matchId.isEmpty()) {
            Log.d("tttt", "matchId: " + matchId);
            activity.startActivity(PlayerActivity.getLaunchIntentForMatchId(getCurrentActivity(), matchId));
        }
    }

    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS;
    }
}
