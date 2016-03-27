package com.github.mzule.androidweekly.dao;

import android.content.Context;
import android.content.SharedPreferences;

import com.github.mzule.androidweekly.App;
import com.github.mzule.androidweekly.entity.Issue;
import com.github.mzule.androidweekly.util.JsonUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by CaoDongping on 3/26/16.
 */
public class IssueListKeeper {
    public static void save(List<Issue> issues) {
        getSharedPreferences().edit().putString("issues", JsonUtil.toJson(issues)).apply();
    }

    public static List<Issue> read() {
        String json = getSharedPreferences().getString("issues", null);
        Issue[] issues = JsonUtil.fromJson(json, Issue[].class);
        if (issues != null) {
            return Arrays.asList(issues);
        } else {
            return Collections.emptyList();
        }
    }

    private static SharedPreferences getSharedPreferences() {
        return App.getInstance().getSharedPreferences("IssueList", Context.MODE_PRIVATE);
    }
}
