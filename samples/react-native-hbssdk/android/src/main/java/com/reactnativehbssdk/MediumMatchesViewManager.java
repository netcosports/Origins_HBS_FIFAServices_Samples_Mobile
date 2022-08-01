package com.reactnativehbssdk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.originsdigital.hbswidgets.matchcenter.MediumMatchesWidget;

public class MediumMatchesViewManager extends SimpleViewManager<MediumMatchesWidget> {

    public static final String REACT_CLASS = "MediumMatches";
    ReactApplicationContext mCallerContext;

    public MediumMatchesViewManager(ReactApplicationContext reactContext) {
        mCallerContext = reactContext;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected MediumMatchesWidget createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new MediumMatchesWidget(reactContext, null);
    }

    @ReactProp(name = "data")
    public void setData(MediumMatchesWidget view, @Nullable ReadableMap data) {
        if (data.hasKey("groupId")) {
            view.setGroupId(data.getString("groupId"));
        } else if (data.hasKey("teamId")) {
            view.setTeamId(data.getString("teamId"));
        } else {
            view.setRoundId(data.getString("roundId"));
        }
    }
}
