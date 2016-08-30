package com.github.mzule.androidweekly.ui.view.base;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.github.mzule.layoutannotation.LayoutBinder;

import butterknife.ButterKnife;

/**
 * Created by CaoDongping on 8/30/16.
 */

public class BaseRelativeLayout extends RelativeLayout {

    public BaseRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutBinder.bind(this);
        ButterKnife.bind(this);
    }

}
