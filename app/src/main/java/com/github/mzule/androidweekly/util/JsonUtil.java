package com.github.mzule.androidweekly.util;

import com.google.gson.Gson;

import java.lang.reflect.Type;

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
}
