package com.reactnativehbssdk;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.originsdigital.hbswidgets.championship.ChampionshipWidget;

public class ChampionshipViewManager extends SimpleViewManager<ChampionshipWidget> {

    public static final String REACT_CLASS = "Championship";
    ReactApplicationContext mCallerContext;

    public ChampionshipViewManager(ReactApplicationContext reactContext) {
        mCallerContext = reactContext;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected ChampionshipWidget createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new ChampionshipWidget(reactContext, null);
    }
}