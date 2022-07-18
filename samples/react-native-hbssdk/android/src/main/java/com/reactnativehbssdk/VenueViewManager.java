package com.reactnativehbssdk;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.originsdigital.hbswidgets.venue.VenuesWidget;

public class VenueViewManager extends SimpleViewManager<VenuesWidget> {

    public static final String REACT_CLASS = "Venue";
    ReactApplicationContext mCallerContext;

    public VenueViewManager(ReactApplicationContext reactContext) {
        mCallerContext = reactContext;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected VenuesWidget createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new VenuesWidget(reactContext, null);
    }
}