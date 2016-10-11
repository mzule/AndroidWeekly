package com.github.mzule.androidweekly.ui.view.base;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.github.mzule.layoutannotation.LayoutBinder;

import butterknife.ButterKnife;

/**
 * Created by CaoDongping on 8/30/16.
 */

public class BaseLinearLayout extends LinearLayout {

    public BaseLinearLayout(Context context) {
        super(context);
        init(context, null);
    }

    public BaseLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BaseLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    protected void init(Context context, AttributeSet attrs) {
        LayoutBinder.bind(this);
        ButterKnife.bind(this);
    }

}
