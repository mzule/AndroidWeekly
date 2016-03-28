package com.github.mzule.androidweekly.dao;

import android.content.Context;
import android.content.SharedPreferences;

import com.github.mzule.androidweekly.App;
import com.github.mzule.androidweekly.entity.Article;
import com.github.mzule.androidweekly.entity.Favorite;
import com.github.mzule.androidweekly.util.JsonUtil;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Created by CaoDongping on 3/28/16.
 */
public class FavoriteKeeper {

    public static List<Favorite> read() {
        String json = getSharedPreferences().getString("favorites", null);
        List<Favorite> favorites = JsonUtil.fromJson(json, new TypeToken<List<Favorite>>() {
        }.getType());
        return favorites == null ? new ArrayList<Favorite>() : favorites;
    }

    public static boolean contains(Article article) {
        return read().contains(new Favorite(article));
    }

    public static void save(Article article) {
        save(new Favorite(article));
    }

    public static void delete(Article article) {
        List<Favorite> favorites = read();
        Iterator<Favorite> iterator = favorites.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getArticle().equals(article)) {
                iterator.remove();
                break;
            }
        }
        save(favorites);
    }

    private static void save(Favorite favorite) {
        List<Favorite> favorites = read();
        if (!favorites.contains(favorite)) {
            favorites.add(0, favorite);
            save(favorites);
        }
    }

    private static void save(List<Favorite> favorites) {
        Collections.sort(favorites, new Comparator<Favorite>() {
            @Override
            public int compare(Favorite lhs, Favorite rhs) {
                return lhs.getTime() - rhs.getTime() > 0 ? -1 : 1;
            }
        });
        getSharedPreferences().edit().putString("favorites", JsonUtil.toJson(favorites)).apply();
    }

    private static SharedPreferences getSharedPreferences() {
        return App.getInstance().getSharedPreferences("Favorite", Context.MODE_PRIVATE);
    }
}
