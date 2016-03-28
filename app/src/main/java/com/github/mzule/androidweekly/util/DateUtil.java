package com.github.mzule.androidweekly.util;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by CaoDongping on 3/28/16.
 */
public class DateUtil {
    @SuppressLint("SimpleDateFormat")
    private static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public static String format(long time) {
        return format.format(time);
    }
}
