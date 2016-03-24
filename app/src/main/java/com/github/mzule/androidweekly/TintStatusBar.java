package com.github.mzule.androidweekly;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 占位符View,在支持Tint的设备上,高度为信号栏的高度,在不支持Tint的设备上,高度为0,可以用来作为UI布局的占位符
 * Created by CaoDongping on 11/11/15.
 */
public class TintStatusBar extends View {
    private int statusBarHeight;

    public TintStatusBar(Context context) {
        super(context);
        init();
    }

    public TintStatusBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TintStatusBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        statusBarHeight = getStatusBarHeight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            int height = 0;
            if (Tinter.isSupport()) {
                height = statusBarHeight;
            }
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public void setHeightPercent(float percent) {
        ViewGroup.LayoutParams lp = getLayoutParams();
        lp.height = (int) (statusBarHeight * percent);
        setLayoutParams(lp);
    }
}
