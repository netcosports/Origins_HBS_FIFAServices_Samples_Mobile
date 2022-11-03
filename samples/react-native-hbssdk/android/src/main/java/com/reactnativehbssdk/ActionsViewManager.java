package com.reactnativehbssdk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.originsdigital.hbswidgets.action.ActionWidget;
import com.originsdigital.hbswidgets.matchdetails.lineup.LineupWidget;

public class ActionsViewManager extends SimpleViewManager<ActionWidget> {

    public static final String REACT_CLASS = "Actions";
    ReactApplicationContext mCallerContext;

    public ActionsViewManager(ReactApplicationContext reactContext) {
        mCallerContext = reactContext;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected ActionWidget createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new ActionWidget(reactContext, null);
    }

    @ReactProp(name = "data")
    public void setData(ActionWidget view, @Nullable ReadableMap data) {
        if (data == null) {
            return;
        }
        String matchId = data.getString("matchId");
        if (matchId != null) {
            view.setupMatchId(matchId);
        }
    }
}