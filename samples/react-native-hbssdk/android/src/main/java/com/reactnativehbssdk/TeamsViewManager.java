package com.reactnativehbssdk;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.originsdigital.hbswidgets.teams.TeamsWidget;

public class TeamsViewManager extends SimpleViewManager<TeamsWidget> {

    public static final String REACT_CLASS = "TeamList";
    ReactApplicationContext mCallerContext;

    public TeamsViewManager(ReactApplicationContext reactContext) {
        mCallerContext = reactContext;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected TeamsWidget createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new TeamsWidget(reactContext, null);
    }
}
