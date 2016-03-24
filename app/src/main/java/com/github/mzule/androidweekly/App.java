package com.github.mzule.androidweekly;

import android.app.Application;

/**
 * Created by CaoDongping on 3/24/16.
 */
public class App extends Application {
    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
