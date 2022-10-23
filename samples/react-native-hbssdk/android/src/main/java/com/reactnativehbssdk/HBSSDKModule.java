package com.reactnativehbssdk;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.originsdigital.hbswidgets.action.ActionWidget;
import com.originsdigital.hbswidgets.core.HbsSdk;
import com.originsdigital.hbswidgets.matchcenter.expanded.ExpandedMatchesWidget;
import com.originsdigital.hbswidgets.matchcenter.large.LargeMatchesWidget;
import com.originsdigital.hbswidgets.matchcenter.medium.MediumMatchesWidget;
import com.originsdigital.hbswidgets.matchcenter.small.SmallMatchesWidget;
import com.originsdigital.hbswidgets.matchdetails.header.MatchDetailsHeaderExpandedWidget;
import com.originsdigital.hbswidgets.matchdetails.header.MatchDetailsHeaderWidget;
import com.originsdigital.hbswidgets.standings.StandingsWidget;
import com.originsdigital.hbswidgets.stats.teammatches.TeamMatchesWidget;
import com.originsdigital.hbswidgets.stats.topplayer.TopPlayerStatsWidget;
import com.originsdigital.hbswidgets.teamboard.TeamBoardWidget;
import com.originsdigital.hbswidgets.teamdetails.squad.SquadWidget;
import com.originsdigital.hbswidgets.venue.VenuesWidget;
import com.originsdigital.hbswidgets.video.VideoCarouselWidget;

import java.util.HashMap;
import java.util.Map;

public class HBSSDKModule extends ReactContextBaseJavaModule {

    public static final String REACT_CLASS = "HBSSDK";
    private final Context context;

    HBSSDKModule(ReactApplicationContext context) {
        super(context);
        this.context = context;
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

        map.put("standingsComponentHeight", StandingsWidget.widgetHeightDp(context));
        map.put("videosComponentHeight", VideoCarouselWidget.widgetHeightDp(context));
        map.put("mediumMatchesComponentHeight", MediumMatchesWidget.widgetHeightDp(context));
        map.put("smallMatchesComponentHeight", SmallMatchesWidget.widgetHeight(context));
        map.put("topPlayerStatsComponentHeight", TopPlayerStatsWidget.widgetHeight(context));
        map.put("teamMatchesStatsComponentHeight", TeamMatchesWidget.widgetHeightDp(context));
        map.put("venueComponentHeight", VenuesWidget.widgetHeight(context));
        map.put("teamBoardComponentHeight", TeamBoardWidget.widgetHeightDp(context));
        map.put("headToHeadComponentHeight", 1040);
        map.put("largeMatchesComponentHeight", LargeMatchesWidget.widgetHeightDp(context));
        map.put("expandedMatchesComponentHeight", ExpandedMatchesWidget.widgetHeightDp(context));
        map.put("squadComponentHeight", SquadWidget.widgetHeight(context));


        map.put("matchHeaderComponentHeight", MatchDetailsHeaderWidget.widgetHeightDp(context));
        map.put("expandedMatchHeaderComponentHeight", MatchDetailsHeaderExpandedWidget.widgetHeightDp(context));
        map.put("actionsComponentHeight", ActionWidget.widgetHeightDp(context));

        map.put("teamMatchesComponentHeight", 310);
        map.put("watchComponentHeight", 510);

        return map;
    }
}
