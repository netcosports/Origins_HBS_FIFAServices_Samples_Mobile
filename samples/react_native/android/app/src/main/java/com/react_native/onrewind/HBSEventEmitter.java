package com.react_native.onrewind;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.originsdigital.hbswidgets.core.HbsSdk;

public class HBSEventEmitter extends ReactContextBaseJavaModule {

    public static final String REACT_CLASS = "HBSEventEmitter";

    private final ReactApplicationContext context;

    HBSEventEmitter(ReactApplicationContext context) {
        super(context);
        this.context = context;
    }

    private void sendEvent(ReactContext reactContext,
                           String eventName,
                           @Nullable WritableMap params) {
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }

    @ReactMethod
    public void addListener(String eventName) {
        HbsSdk.setOnVideoClickListener(new HbsSdk.OnVideoClickListener() {
            @Override
            public void playVideoUrl(@NonNull Context context, @NonNull String s) {
                WritableMap params = Arguments.createMap();
                params.putString("streamUrl", s);
                params.putString("matchId", "");
                sendEvent(HBSEventEmitter.this.context, "presentVideoPlayer", params);
            }

            @Override
            public void playEventId(@NonNull Context context, @NonNull String s) {
                WritableMap params = Arguments.createMap();
                params.putString("streamUrl", "");
                params.putString("matchId", s);
                sendEvent(HBSEventEmitter.this.context, "presentVideoPlayer", params);
            }
        });
    }

    @ReactMethod
    public void removeListeners(Integer count) {
        // Remove upstream listeners, stop unnecessary background tasks
    }

    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS;
    }
}
