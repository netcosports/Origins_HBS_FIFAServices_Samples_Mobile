package com.react_native.onrewind;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class OnRewindModule extends ReactContextBaseJavaModule {

    public static final String REACT_CLASS = "OnRewind";

    private final Context context;

    OnRewindModule(ReactApplicationContext context) {
        super(context);
        this.context = context;
    }

    @ReactMethod
    public void presentPlayer(String matchId, String streamUrl) {
        if (!videoUrl.isEmpty()) {
            Log.d("tttt", "video url: " + videoUrl);
        } else if (!matchId.isEmpty()) {
            Log.d("tttt", "matchId: " + matchId);
        }
    }

    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS;
    }
}
