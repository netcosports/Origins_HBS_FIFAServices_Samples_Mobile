package com.reactnativehbssdk;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.originsdigital.hbswidgets.video.VideoCarouselWidget;


public class VideosViewManager extends SimpleViewManager<VideoCarouselWidget> {

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
    protected VideoCarouselWidget createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new VideoCarouselWidget(reactContext, null);
    }

    @ReactProp(name = "data")
    public void setData(VideoCarouselWidget view, @Nullable ReadableMap data) {
        if (data == null) {
            return;
        }
        String category = data.getString("category");

        String title = category;
        if (data.hasKey("title")) {
            title = data.getString("title");
        }
        String subcategory = null;
        if (data.hasKey("subcategory")) {
            subcategory = data.getString("subcategory");
        }
        String matchId = null;
        if (data.hasKey("matchId")) {
            matchId = data.getString("matchId");
        }
        if (matchId != null) {
            view.setMatchId(matchId, title);
        } else if (category != null) {
            view.setCategory(category, subcategory, title != null ? title : category);
        }
    }
}
