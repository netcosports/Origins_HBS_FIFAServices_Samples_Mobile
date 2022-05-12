package com.hbswidgetsdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hbswidgetsdemo.R;
import com.originsdigital.hbswidgets.stats.topplayer.view.TopPlayerStatsItemView;
import com.originsdigital.hbswidgets.stats.topplayer.view.TopPlayerStatsView;

public class TestWidget extends FrameLayout {
    public TestWidget(@NonNull Context context) {
        super(context);
        initLayout();
    }

    public TestWidget(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initLayout();
    }

    public TestWidget(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout();
    }

    private void initLayout() {
        LayoutInflater.from(getContext()).inflate(R.layout.test_widget_view, this, true);
        final TextView view1 = findViewById(R.id.view1);
        final TextView view2 = findViewById(R.id.view2);
        view1.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.d("tttt", "view1: " + view1.getWidth() + " - " + view1.getHeight());
            }
        });
        view2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.d("tttt", "view2: " + view2.getWidth() + " - " + view2.getHeight());
            }
        });
        view1.setText("Text 1 top");
        post(new Runnable() {
            @Override
            public void run() {
                view2.setText("Text 2 bottom");
            }
        });




//        TopPlayerStatsView playerView = findViewById(R.id.player);
//        post(new Runnable() {
//            @Override
//            public void run() {
//
//                playerView.setTestData();
//            }
//        });
    }
}
