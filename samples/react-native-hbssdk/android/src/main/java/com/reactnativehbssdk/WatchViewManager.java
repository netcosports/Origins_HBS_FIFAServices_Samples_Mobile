package com.reactnativehbssdk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.originsdigital.hbswidgets.watch.WatchWidget;

public class WatchViewManager extends SimpleViewManager<WatchWidget> {

    public static final String REACT_CLASS = "Watch";
    ReactApplicationContext mCallerContext;

    public WatchViewManager(ReactApplicationContext reactContext) {
        mCallerContext = reactContext;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected WatchWidget createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new WatchWidget(reactContext, null);
    }

    // FIXME: do we need to provide something here?
    @ReactProp(name = "teamId")
    public void setTeamId(WatchWidget view, @Nullable String teamId) {

    }
}