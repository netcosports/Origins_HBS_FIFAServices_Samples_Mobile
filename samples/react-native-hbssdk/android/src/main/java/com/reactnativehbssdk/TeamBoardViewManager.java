package com.reactnativehbssdk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.originsdigital.hbswidgets.teamboard.TeamBoardWidget;

public class TeamBoardViewManager extends SimpleViewManager<TeamBoardWidget> {

    public static final String REACT_CLASS = "TeamBoard";
    ReactApplicationContext mCallerContext;

    public TeamBoardViewManager(ReactApplicationContext reactContext) {
        mCallerContext = reactContext;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected TeamBoardWidget createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new TeamBoardWidget(reactContext, null);
    }

    @ReactProp(name = "teamId")
    public void setTeamId(TeamBoardWidget view, @Nullable String teamId) {
        view.setupTeamId(teamId);
    }
}