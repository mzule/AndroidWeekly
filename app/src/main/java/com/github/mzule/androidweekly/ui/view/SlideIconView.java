package com.github.mzule.androidweekly.ui.view;

import android.content.Context;
import android.util.AttributeSet;

import com.github.mzule.androidweekly.R;

import net.steamcrafted.materialiconlib.MaterialIconView;

/**
 * Created by CaoDongping on 3/27/16.
 */
public class SlideIconView extends MaterialIconView {
    public SlideIconView(Context context) {
        super(context);
    }

    public SlideIconView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlideIconView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        if (pressed) {
            setColor(getResources().getColor(R.color.white));
            setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else {
            setColor(getResources().getColor(R.color.colorPrimary));
            setBackgroundColor(getResources().getColor(R.color.transparent));
        }
    }
}
