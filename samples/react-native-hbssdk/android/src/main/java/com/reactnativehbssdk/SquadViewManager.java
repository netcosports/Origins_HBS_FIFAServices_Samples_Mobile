package com.reactnativehbssdk;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.originsdigital.hbswidgets.action.ActionWidget;
import com.originsdigital.hbswidgets.teamdetails.squad.SquadWidget;

public class SquadViewManager extends SimpleViewManager<SquadWidget> {

    public static final String REACT_CLASS = "Squad";
    ReactApplicationContext mCallerContext;

    public SquadViewManager(ReactApplicationContext reactContext) {
        mCallerContext = reactContext;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected SquadWidget createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new SquadWidget(reactContext, null);
    }

    @ReactProp(name = "data")
    public void setData(SquadWidget view, @Nullable ReadableMap data) {
        if (data == null) {
            return;
        }
        String teamId = data.getString("teamId");
        Log.d("tttt", "teamId: " + teamId);
        if (teamId != null) {
            view.setupTeamId(teamId);
        }
    }
}