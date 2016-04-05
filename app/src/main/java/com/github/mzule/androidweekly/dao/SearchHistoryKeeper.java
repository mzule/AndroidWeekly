package com.github.mzule.androidweekly.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.github.mzule.androidweekly.App;
import com.github.mzule.androidweekly.util.JsonUtil;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaoDongping on 4/5/16.
 */
public class SearchHistoryKeeper {

    private static final int MAX_SIZE = 5;

    public static void save(String q) {
        List<String> exist = read();
        if (exist.contains(q)) {
            exist.remove(q);
        }
        exist.add(0, q);
        if (exist.size() > MAX_SIZE) {
            exist = exist.subList(0, MAX_SIZE);
        }
        getSharedPreferences().edit().putString("q", JsonUtil.toJson(exist)).apply();
    }

    public static List<String> read() {
        String json = getSharedPreferences().getString("q", null);
        if (TextUtils.isEmpty(json)) {
            return new ArrayList<>();
        } else {
            return JsonUtil.fromJson(json, new TypeToken<List<String>>() {
            }.getType());
        }
    }

    private static SharedPreferences getSharedPreferences() {
        return App.getInstance().getSharedPreferences("SearchHistory", Context.MODE_PRIVATE);
    }
}
