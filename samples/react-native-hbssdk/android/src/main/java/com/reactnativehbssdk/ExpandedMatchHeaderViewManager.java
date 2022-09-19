package com.reactnativehbssdk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.originsdigital.hbswidgets.matchdetails.header.MatchDetailsHeaderExpandedWidget;
import com.originsdigital.hbswidgets.matchdetails.header.MatchDetailsHeaderWidget;

public class ExpandedMatchHeaderViewManager extends SimpleViewManager<MatchDetailsHeaderExpandedWidget> {

    public static final String REACT_CLASS = "ExpandedMatchHeader";
    ReactApplicationContext mCallerContext;

    public ExpandedMatchHeaderViewManager(ReactApplicationContext reactContext) {
        mCallerContext = reactContext;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected MatchDetailsHeaderExpandedWidget createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new MatchDetailsHeaderExpandedWidget(reactContext, null);
    }

    @ReactProp(name = "data")
    public void setData(MatchDetailsHeaderExpandedWidget view, @Nullable ReadableMap data) {
        if (data == null) {
            return;
        }
        String matchId = data.getString("matchId");
        if (matchId != null) {
            view.setupMatchId(matchId);
        }
    }
}
