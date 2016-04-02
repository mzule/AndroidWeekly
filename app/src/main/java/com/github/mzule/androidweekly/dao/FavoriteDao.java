package com.github.mzule.androidweekly.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.github.mzule.androidweekly.App;
import com.github.mzule.androidweekly.entity.Article;
import com.github.mzule.androidweekly.entity.Favorite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaoDongping on 4/2/16.
 */
public class FavoriteDao extends SQLiteOpenHelper {
    private static final String NAME = "favorite";
    private static final int VERSION = 1;
    private static final int INDEX_TITLE = 1;
    private static final int INDEX_BRIEF = 2;
    private static final int INDEX_LINK = 3;
    private static final int INDEX_IMAGE_URL = 4;
    private static final int INDEX_DOMAIN = 5;
    private static final int INDEX_ISSUE = 6;
    private static final int INDEX_SECTION = 7;
    private static final int INDEX_TYPE = 8;
    private static final int INDEX_TIME = 9;

    public FavoriteDao() {
        super(App.getInstance(), NAME, null, VERSION);
    }

    public List<Favorite> read() {
        List<Favorite> favorites = new ArrayList<>();
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM FAVORITE ORDER BY TIME DESC", null);
        while (cursor.moveToNext()) {
            Article article = new Article();
            article.setTitle(cursor.getString(INDEX_TITLE));
            article.setBrief(cursor.getString(INDEX_BRIEF));
            article.setLink(cursor.getString(INDEX_LINK));
            article.setImageUrl(cursor.getString(INDEX_IMAGE_URL));
            article.setDomain(cursor.getString(INDEX_DOMAIN));
            article.setIssue(cursor.getString(INDEX_ISSUE));
            article.setSection(cursor.getString(INDEX_SECTION));
            Favorite favorite = new Favorite(article);
            favorite.setTime(cursor.getLong(INDEX_TIME));
            favorites.add(favorite);
        }
        cursor.close();
        return favorites;
    }

    public boolean contains(Article article) {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT COUNT(*) FROM FAVORITE WHERE LINK=? AND ISSUE=?", new String[]{article.getLink(), article.getIssue()});
        cursor.moveToNext();
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
    }

    public void save(Article article) {
        if (!contains(article)) {
            ContentValues cv = new ContentValues();
            cv.put("TITLE", article.getTitle());
            cv.put("BRIEF", article.getBrief());
            cv.put("LINK", article.getLink());
            cv.put("IMAGE_URL", article.getImageUrl());
            cv.put("DOMAIN", article.getDomain());
            cv.put("ISSUE", article.getIssue());
            cv.put("SECTION", article.getSection());
            cv.put("TYPE", 0);// TODO add type for article
            cv.put("TIME", System.currentTimeMillis());
            getWritableDatabase().insert("FAVORITE", null, cv);
        }
    }

    public void delete(Article article) {
        getWritableDatabase().delete("FAVORITE", "LINK=? AND ISSUE=?", new String[]{article.getLink(), article.getIssue()});
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 不创建一个与Article的外键引用是因为Article表是cache，可能会被清除。
        db.execSQL("CREATE TABLE IF NOT EXISTS FAVORITE(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "TITLE VARCHAR(255), " +
                "BRIEF TEXT, " +
                "LINK VARCHAR(255), " +
                "IMAGE_URL VARCHAR(255), " +
                "DOMAIN VARCHAR(255), " +
                "ISSUE VARCHAR(255), " +
                "SECTION VARCHAR(255), " +
                "TYPE INTEGER, " +
                "TIME INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
