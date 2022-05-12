package com.hbswidgetsdemo.hbswidgets;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

import com.facebook.react.uimanager.annotations.ReactProp;
import com.originsdigital.hbswidgets.stats.topplayer.TopPlayerStatsWidget;

import java.util.HashMap;
import java.util.Map;

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
        TopPlayerStatsWidget widget = new TopPlayerStatsWidget(reactContext, null);
        widget.setupStatsType(TopPlayerStatsWidget.Type.GOALS);
        return widget;
    }

    @ReactProp(name = "statType")
    public void setStatType(TopPlayerStatsWidget view, @Nullable String statType) {
        //view.setupStatsType("goals".equals(statType) ? TopPlayerStatsWidget.Type.GOALS : TopPlayerStatsWidget.Type.ASSISTS);
    }
}