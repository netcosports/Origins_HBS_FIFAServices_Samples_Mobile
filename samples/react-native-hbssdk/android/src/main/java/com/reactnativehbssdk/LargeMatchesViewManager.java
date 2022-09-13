package com.reactnativehbssdk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.originsdigital.hbswidgets.matchcenter.large.LargeMatchesWidget;

public class LargeMatchesViewManager extends SimpleViewManager<LargeMatchesWidget> {

    public static final String REACT_CLASS = "LargeMatches";
    ReactApplicationContext mCallerContext;

    public LargeMatchesViewManager(ReactApplicationContext reactContext) {
        mCallerContext = reactContext;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected LargeMatchesWidget createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new LargeMatchesWidget(reactContext, null);
    }

    @ReactProp(name = "data")
    public void setData(LargeMatchesWidget view, @Nullable ReadableMap data) {
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
