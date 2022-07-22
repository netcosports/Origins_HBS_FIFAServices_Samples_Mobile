package com.reactnativehbssdk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.originsdigital.hbswidgets.standings.StandingsWidget;

public class StandingsViewManager extends SimpleViewManager<StandingsWidget> {

    public static final String REACT_CLASS = "Standings";
    ReactApplicationContext mCallerContext;

    public StandingsViewManager(ReactApplicationContext reactContext) {
        mCallerContext = reactContext;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected StandingsWidget createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new StandingsWidget(reactContext, null);
    }

    @ReactProp(name = "data")
    public void setData(StandingsWidget view, @Nullable ReadableMap data) {
        view.setupDisplayParams(data.getBoolean("isExpanded"));
        if (data.hasKey("groupId")) {
            view.setupSingleGroup(data.getString("groupId"));
        } else {
            view.setupAllGroups();
        }
        view.setupDisplayParams(false);
    }
}
