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
        map.put("topPlayerStatsComponentHeight", 250);
        map.put("teamMatchesStatsComponentHeight", 310);
        map.put("venueComponentHeight", 600);//todo discuss
        map.put("teamBoardComponentHeight", 1380);
        map.put("headToHeadComponentHeight", 1040);
        map.put("largeMatchesComponentHeight", 280);
        map.put("expandedMatchesComponentHeight", 360);

        map.put("favoritesComponentHeight", 260);
        map.put("championshipComponentHeight", 260);
        map.put("matchCenterComponentHeight", 260);

        map.put("teamMatchesComponentHeight", 310);
        map.put("watchComponentHeight", 260);

        return map;
    }
}