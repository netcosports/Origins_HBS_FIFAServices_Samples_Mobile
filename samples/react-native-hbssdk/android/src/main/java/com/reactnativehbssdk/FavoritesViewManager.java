package com.reactnativehbssdk;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.originsdigital.hbswidgets.favorite.FavoritesWidget;

public class FavoritesViewManager extends SimpleViewManager<FavoritesWidget> {

    public static final String REACT_CLASS = "Favorites";
    ReactApplicationContext mCallerContext;

    public FavoritesViewManager(ReactApplicationContext reactContext) {
        mCallerContext = reactContext;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected FavoritesWidget createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new FavoritesWidget(reactContext, null);
    }
}
