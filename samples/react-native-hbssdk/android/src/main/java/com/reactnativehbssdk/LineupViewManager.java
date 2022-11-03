package com.reactnativehbssdk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.originsdigital.hbswidgets.matchdetails.lineup.LineupWidget;

public class LineupViewManager extends SimpleViewManager<LineupWidget> {

    public static final String REACT_CLASS = "Lineup";
    ReactApplicationContext mCallerContext;

    public LineupViewManager(ReactApplicationContext reactContext) {
        mCallerContext = reactContext;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected LineupWidget createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new LineupWidget(reactContext, null);
    }

    @ReactProp(name = "data")
    public void setData(LineupWidget view, @Nullable ReadableMap data) {
        if (data == null) {
            return;
        }
        String matchId = data.getString("matchId");
        if (matchId != null) {
            view.setupMatchId(matchId);
        }
    }
}