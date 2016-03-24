package com.github.mzule.androidweekly.util;


import com.github.mzule.androidweekly.App;

/**
 * Created by CaoDongping on 11/4/15.
 */
public class DensityUtil {
    public static int dp2px(float dp) {
        float density = App.getInstance().getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);
    }

    public static float px2dp(float px) {
        float density = App.getInstance().getResources().getDisplayMetrics().density;
        return px / density;
    }

    public static int screenWidth() {
        return App.getInstance().getResources().getDisplayMetrics().widthPixels;
    }
}
