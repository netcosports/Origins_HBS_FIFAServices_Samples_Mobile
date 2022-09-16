package com.reactnativehbssdk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.originsdigital.hbswidgets.matchdetails.lineup.LineupWidget;
import com.originsdigital.hbswidgets.matchdetails.stats.MatchStatsWidget;

public class MatchStatsViewManager extends SimpleViewManager<MatchStatsWidget> {

    public static final String REACT_CLASS = "MatchStats";
    ReactApplicationContext mCallerContext;

    public MatchStatsViewManager(ReactApplicationContext reactContext) {
        mCallerContext = reactContext;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected MatchStatsWidget createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new MatchStatsWidget(reactContext, null);
    }

    @ReactProp(name = "data")
    public void setData(MatchStatsWidget view, @Nullable ReadableMap data) {
        if (data == null) {
            return;
        }
        String matchId = data.getString("matchId");
        if (matchId != null) {
            view.setupMatchId(matchId);
        }
    }
}