package com.github.mzule.androidweekly.util;

import android.support.annotation.WorkerThread;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;

/**
 * Created by CaoDongping on 3/26/16.
 */
public class JsonUtil {
    private static final Gson gson = new Gson();

    public static String toJson(Object obj) {
        try {
            return gson.toJson(obj);
        } catch (Throwable e) {
            return "";
        }
    }

    public static <T> T fromJson(String json, Class<T> cls) {
        try {
            return gson.fromJson(json, cls);
        } catch (Throwable e) {
            return null;
        }
    }

    public static <T> T fromJson(String json, Type type) {
        try {
            return gson.fromJson(json, type);
        } catch (Throwable e) {
            return null;
        }
    }

    @WorkerThread
    public static <T> T fromJson(URL url, Class<T> cls) {
        try {
            return gson.fromJson(new BufferedReader(new InputStreamReader(url.openConnection().getInputStream())), cls);
        } catch (Throwable e) {
            return null;
        }
    }
}
