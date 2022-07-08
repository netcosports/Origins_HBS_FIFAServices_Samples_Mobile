package com.reactnativehbssdk;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.originsdigital.hbswidgets.video.VideosWidget;


public class VideosViewManager extends SimpleViewManager<VideosWidget> {

    public static final String REACT_CLASS = "Videos";
    ReactApplicationContext mCallerContext;

    public VideosViewManager(ReactApplicationContext reactContext) {
        mCallerContext = reactContext;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected VideosWidget createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new VideosWidget(reactContext, null);
    }
//
//    @ReactProp(name = "teamId")
//    public void setTeamId(TeamMatchesWidget view, @Nullable String teamId) {
//        view.setupParams(teamId);
//    }
}