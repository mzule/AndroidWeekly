package com.github.mzule.androidweekly.dao;

import android.content.Context;
import android.content.SharedPreferences;

import com.github.mzule.androidweekly.App;
import com.github.mzule.androidweekly.entity.Article;
import com.github.mzule.androidweekly.util.JsonUtil;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaoDongping on 3/28/16.
 */
public class FavoriteKeeper {

    public static List<Article> read() {
        String json = getSharedPreferences().getString("articles", null);
        List<Article> articles = JsonUtil.fromJson(json, new TypeToken<List<Article>>() {
        }.getType());
        return articles == null ? new ArrayList<Article>() : articles;
    }

    public static void save(Article article) {
        List<Article> articles = read();
        if (!articles.contains(article)) {
            articles.add(0, article);
            save(articles);
        }
    }

    public static void delete(Article article) {
        List<Article> articles = read();
        articles.remove(article);
        save(articles);
    }

    private static void save(List<Article> articles) {
        getSharedPreferences().edit().putString("articles", JsonUtil.toJson(articles)).apply();
    }

    private static SharedPreferences getSharedPreferences() {
        return App.getInstance().getSharedPreferences("Favorite", Context.MODE_PRIVATE);
    }
}
