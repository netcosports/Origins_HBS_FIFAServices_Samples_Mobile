package com.react_native.onrewind;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.originsdigital.hbswidgets.core.HbsSdk;
import com.react_native.PreferenceUtils;

public class HbsConfigurationModule extends ReactContextBaseJavaModule {

    public static final String REACT_CLASS = "HbsConfiguration";

    public HbsConfigurationModule(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @ReactMethod
    public void updateConfiguration(String competition, String season) {
        PreferenceUtils.saveConfiguration(getReactApplicationContext(), competition, season);
        HbsSdk.changeCompetitionConfiguration(competition, season);
    }
}
