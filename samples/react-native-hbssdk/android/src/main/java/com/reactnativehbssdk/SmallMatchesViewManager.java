package com.reactnativehbssdk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.originsdigital.hbswidgets.matchcenter.small.SmallMatchesWidget;

public class SmallMatchesViewManager extends SimpleViewManager<SmallMatchesWidget> {

    public static final String REACT_CLASS = "SmallMatches";
    ReactApplicationContext mCallerContext;

    public SmallMatchesViewManager(ReactApplicationContext reactContext) {
        mCallerContext = reactContext;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected SmallMatchesWidget createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new SmallMatchesWidget(reactContext, null);
    }

    @ReactProp(name = "data")
    public void setData(SmallMatchesWidget view, @Nullable ReadableMap data) {
        if (data.hasKey("groupId")) {
            view.setGroupId(data.getString("groupId"));
        } else if (data.hasKey("teamId")) {
            view.setTeamId(data.getString("teamId"));
        } else if (data.hasKey("roundId")) {
            view.setRoundId(data.getString("roundId"));
        } else if (data.hasKey("matchId")) {
            view.setMatchId(data.getString("matchId"));
        }
    }
}
