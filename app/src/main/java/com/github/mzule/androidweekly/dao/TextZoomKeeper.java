package com.github.mzule.androidweekly.dao;

import android.content.Context;
import android.content.SharedPreferences;

import com.github.mzule.androidweekly.App;

/**
 * Created by CaoDongping on 3/26/16.
 */
public class TextZoomKeeper {
    public static void save(int zoom) {
        getSharedPreferences().edit().putInt("zoom", zoom).apply();
    }

    public static int read(int def) {
        return getSharedPreferences().getInt("zoom", def);
    }

    private static SharedPreferences getSharedPreferences() {
        return App.getInstance().getSharedPreferences("TextZoom", Context.MODE_PRIVATE);
    }
}
