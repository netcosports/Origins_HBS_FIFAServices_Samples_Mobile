package com.reactnativehbssdk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.originsdigital.hbswidgets.h2h.HeadToHeadWidget;


public class HeadToHeadViewManager extends SimpleViewManager<HeadToHeadWidget> {

    public static final String REACT_CLASS = "HeadToHead";
    ReactApplicationContext mCallerContext;

    public HeadToHeadViewManager(ReactApplicationContext reactContext) {
        mCallerContext = reactContext;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected HeadToHeadWidget createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new HeadToHeadWidget(reactContext, null);
    }

    @ReactProp(name = "data")
    public void setData(HeadToHeadWidget view, @Nullable ReadableMap data) {
        view.setupTeamIds(data.getString("teamId1"), data.getString("teamId2"));
    }
}