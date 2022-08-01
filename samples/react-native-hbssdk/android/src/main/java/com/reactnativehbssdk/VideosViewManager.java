package com.reactnativehbssdk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.originsdigital.hbswidgets.video.VideosListWidget;


public class VideosViewManager extends SimpleViewManager<VideosListWidget> {

    public static final String REACT_CLASS = "Videos";
    ReactApplicationContext mCallerContext;

    public VideosViewManager(ReactApplicationContext reactContext) {
        mCallerContext = reactContext;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected VideosListWidget createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new VideosListWidget(reactContext, null);
    }

    @ReactProp(name = "category")
    public void setCategory(VideosListWidget view, @Nullable String category) {
        view.setCategory(category);
    }
}
