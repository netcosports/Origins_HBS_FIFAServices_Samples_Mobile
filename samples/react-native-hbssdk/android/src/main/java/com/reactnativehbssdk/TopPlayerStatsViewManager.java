package com.reactnativehbssdk;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.originsdigital.hbswidgets.stats.topplayer.TopPlayerStatsWidget;


public class TopPlayerStatsViewManager extends SimpleViewManager<TopPlayerStatsWidget> {

    public static final String REACT_CLASS = "TopPlayerStats";
    ReactApplicationContext mCallerContext;

    public TopPlayerStatsViewManager(ReactApplicationContext reactContext) {
        mCallerContext = reactContext;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected TopPlayerStatsWidget createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new TopPlayerStatsWidget(reactContext, null);
    }

    @ReactProp(name = "data")
    public void setData(TopPlayerStatsWidget view, @Nullable ReadableMap data) {
        String teamId = data.getString("teamId");
        if (teamId == null) {
            return;
        }
        String stringType = data.getString("statsType");
        if (stringType == null) {
            return;
        }
        TopPlayerStatsWidget.Type statsType = null;
        switch (stringType) {
            case "goals":
                statsType = TopPlayerStatsWidget.Type.GOALS;
                break;
            case "shots":
                statsType = TopPlayerStatsWidget.Type.SHOTS;
                break;
        }
        if (statsType == null) {
            return;
        }
        view.setupStatsType(teamId, statsType);
    }
}