package com.reactnativehbssdk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

import java.util.HashMap;
import java.util.Map;

public class HBSSDKModule extends ReactContextBaseJavaModule {

    public static final String REACT_CLASS = "HBSSDK";

    HBSSDKModule(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    public boolean hasConstants() {
        return true;
    }

    @Nullable
    @Override
    public Map<String, Object> getConstants() {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("standingsComponentHeight", 290);
        map.put("videosComponentHeight", 248);
        map.put("mediumMatchesComponentHeight", 95);
        map.put("smallMatchesComponentHeight", 130);

        map.put("teamMatchesComponentHeight", 260);
        map.put("topPlayerStatsComponentHeight", 260);
        map.put("favoritesComponentHeight", 260);
        map.put("championshipComponentHeight", 260);
        map.put("headToHeadComponentHeight", 260);
        map.put("matchCenterComponentHeight", 260);
        map.put("matchesWithEventsComponentHeight", 260);
        map.put("matchesWithoutEventsComponentHeight", 260);
        map.put("teamMatchesStatsComponentHeight", 260);
        map.put("teamBoardComponentHeight", 260);
        map.put("venueComponentHeight", 260);
        map.put("watchComponentHeight", 260);

        return map;
    }
}