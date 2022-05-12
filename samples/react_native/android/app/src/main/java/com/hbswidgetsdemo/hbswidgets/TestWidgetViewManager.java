package com.hbswidgetsdemo.hbswidgets;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.hbswidgetsdemo.widget.TestWidget;

public class TestWidgetViewManager extends SimpleViewManager<TestWidget> {

    ReactApplicationContext mCallerContext;

    public TestWidgetViewManager(ReactApplicationContext reactContext) {
        mCallerContext = reactContext;
    }

    @NonNull
    @Override
    public String getName() {
        return "TestWidget";
    }

    @NonNull
    @Override
    protected TestWidget createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new TestWidget(reactContext, null);
    }
}
