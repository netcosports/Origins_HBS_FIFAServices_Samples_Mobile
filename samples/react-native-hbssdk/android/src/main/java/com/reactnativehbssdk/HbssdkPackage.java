package com.reactnativehbssdk;

import androidx.annotation.NonNull;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.Arrays;
import java.util.List;

public class HbssdkPackage implements ReactPackage {

    @NonNull
    @Override
    public List<NativeModule> createNativeModules(@NonNull ReactApplicationContext reactContext) {
        return Arrays.<NativeModule>asList(
                new HBSSDKModule(reactContext)
        );
    }

    @NonNull
    @Override
    public List<ViewManager> createViewManagers(@NonNull ReactApplicationContext reactContext) {
        return Arrays.<ViewManager>asList(
                new TopPlayerStatsViewManager(reactContext),
                new TeamMatchesViewManager(reactContext),
                new VideosViewManager(reactContext),
                new StandingsViewManager(reactContext),
                new TeamBoardViewManager(reactContext),
                new ChampionshipViewManager(reactContext),
                new FavoritesViewManager(reactContext),
                new HeadToHeadViewManager(reactContext),
                new VenueViewManager(reactContext),
                new WatchViewManager(reactContext),
                new TeamMatchesStatsViewManager(reactContext),
                new SmallMatchesViewManager(reactContext),
                new MediumMatchesViewManager(reactContext),
                new LargeMatchesViewManager(reactContext),
                new ExpandedMatchesViewManager(reactContext),
                new LineupViewManager(reactContext),
                new MatchStatsViewManager(reactContext),
                new MatchHeaderViewManager(reactContext),
                new ExpandedMatchHeaderViewManager(reactContext),
                new SquadViewManager(reactContext),
                new TeamsViewManager(reactContext),
                new ActionsViewManager(reactContext)
        );
    }

}
