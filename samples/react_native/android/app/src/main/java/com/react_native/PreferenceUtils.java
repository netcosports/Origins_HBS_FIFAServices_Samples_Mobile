package com.react_native;

import android.content.Context;
import android.preference.PreferenceManager;

public class PreferenceUtils {

    public static final String PARAM_COMPETITION = "param_competition";
    public static final String PARAM_SEASON = "param_season";

    public static void saveConfiguration(Context context, String competitionId, String season) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(PARAM_COMPETITION, competitionId)
                .putString(PARAM_SEASON, season)
                .apply();
    }

    public static String getCompetition(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(PARAM_COMPETITION, null);
    }

    public static String getSeason(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(PARAM_SEASON, null);
    }
}
