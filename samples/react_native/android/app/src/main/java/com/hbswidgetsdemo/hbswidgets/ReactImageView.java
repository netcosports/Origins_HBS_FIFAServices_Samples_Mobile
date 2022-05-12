package com.hbswidgetsdemo.hbswidgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.io.Serializable;

public class ReactImageView extends androidx.appcompat.widget.AppCompatImageView implements Serializable {

    public ReactImageView(Context context) {
        super(context);
        this.setBackgroundColor(0xFF00FF);
    }

    public ReactImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setBackgroundColor(0xFF00FF);
    }

    public ReactImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setBackgroundColor(0xFF00FF);
    }
}
