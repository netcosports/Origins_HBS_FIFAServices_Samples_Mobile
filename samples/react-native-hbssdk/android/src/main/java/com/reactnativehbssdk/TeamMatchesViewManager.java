package com.reactnativehbssdk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.originsdigital.hbswidgets.stats.teammatches.TeamMatchesWidget;


public class TeamMatchesViewManager extends SimpleViewManager<TeamMatchesWidget> {

    public static final String REACT_CLASS = "TeamMatches";
    ReactApplicationContext mCallerContext;

    public TeamMatchesViewManager(ReactApplicationContext reactContext) {
        mCallerContext = reactContext;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected TeamMatchesWidget createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new TeamMatchesWidget(reactContext, null);
    }

    @ReactProp(name = "teamId")
    public void setTeamId(TeamMatchesWidget view, @Nullable String teamId) {
        view.setupParams(teamId);
    }
}