package com.github.mzule.androidweekly;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * <p/>
 * 设置Activity的信号栏为透明风格的工具类
 * <p/>
 * Created by CaoDongping on 9/17/15.
 */
public class Tinter {

    /**
     * 如果当前系统版本支持Tint,则开启,否则,不作任何事情.
     *
     * @param activity
     */
    public static void enableIfSupport(Activity activity) {
        if (isSupport()) {
            tint(activity, true);
        }
    }

    /**
     * 当前系统版本是否支持Tint
     *
     * @return
     */
    public static boolean isSupport() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    @TargetApi(19)
    private static void tint(Activity activity, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);

        Class<? extends Window> clazz = win.getClass();
        try {
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            int darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(win, darkModeFlag, darkModeFlag);
        } catch (Throwable e) {
        }

    }
}
