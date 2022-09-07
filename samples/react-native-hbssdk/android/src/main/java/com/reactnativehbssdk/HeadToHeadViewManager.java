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
        if (data == null) {
            view.setupNoTeams();
            return;
        }
        if (data.hasKey("teamId")) {
            view.setupOneTeamId(data.getString("teamId"));
        } else if (data.hasKey("teamId1") && data.hasKey("teamId2")) {
            view.setupTwoTeamsIds(data.getString("teamId1"), data.getString("teamId2"));
        } else {
            view.setupNoTeams();
        }
    }
}
