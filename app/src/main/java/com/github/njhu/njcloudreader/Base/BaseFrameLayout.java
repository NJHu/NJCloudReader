package com.github.njhu.njcloudreader.Base;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class BaseFrameLayout extends FrameLayout {
    public BaseFrameLayout(Context context) {
        this(context, null);
    }
    public BaseFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public BaseFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
