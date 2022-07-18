package com.reactnativehbssdk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.originsdigital.hbswidgets.matchcenter.LargeMatchesWidget;

public class LargeMatchesViewManager extends SimpleViewManager<LargeMatchesWidget> {

    public static final String REACT_CLASS = "LargeMatches";
    ReactApplicationContext mCallerContext;

    public LargeMatchesViewManager(ReactApplicationContext reactContext) {
        mCallerContext = reactContext;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected LargeMatchesWidget createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new LargeMatchesWidget(reactContext, null);
    }

    @ReactProp(name = "data")
    public void setData(LargeMatchesWidget view, @Nullable ReadableMap data) {
        if (data.hasKey("groupId")) {
            view.setupWithGroup(data.getString("groupId"));
        } else if (data.hasKey("teamId")) {
            view.setupWithGroup(data.getString("teamId"));
        } else {
            view.setupWithGroup(data.getString("roundId"));
        }
    }
}