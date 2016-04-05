package com.github.mzule.androidweekly.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.github.mzule.androidweekly.R;
import com.github.mzule.androidweekly.util.DensityUtil;
import com.pnikosis.materialishprogress.ProgressWheel;


/**
 * Created by CaoDongping on 1/5/16.
 */
public class ProgressView extends ProgressWheel {
    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgressView(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (isInEditMode()) {
            return;
        }
        setBarColor(getResources().getColor(R.color.colorPrimary));
        setBarWidth(DensityUtil.dp2px(2));
        spin();
    }

    public void start() {
        setVisibility(VISIBLE);
    }

    public void stop() {
        setVisibility(View.GONE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isInEditMode() && getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT && getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            super.onMeasure(DensityUtil.dp2px(40), DensityUtil.dp2px(40));
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
