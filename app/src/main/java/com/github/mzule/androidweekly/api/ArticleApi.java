package com.github.mzule.androidweekly.api;


import android.os.Handler;

import com.github.mzule.androidweekly.entity.Article;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mzule on 3/16/16.
 */
public class ArticleApi {

    private Handler handler = new Handler();

    public void getPage(final ApiCallback<List<Object>> callback) {
        new Thread() {
            @Override
            public void run() {
                doGetPage(callback);
            }
        }.start();
    }

    private void doGetPage(final ApiCallback<List<Object>> callback) {
        try {
            Document doc = Jsoup.parse(new URL("http://androidweekly.net"), 30000);
            Elements tables = doc.getElementsByTag("table");

            final List<Object> articles = new ArrayList<>();

            for (Element e : tables) {
                Elements h2 = e.getElementsByTag("h2");
                if (!h2.isEmpty()) {
                    articles.add(h2.get(0).text());
                } else {
                    Elements tds = e.getElementsByTag("td");
                    Element td = tds.get(tds.size() - 2);
                    String imageUrl = null;
                    if (tds.size() == 4) {
                        imageUrl = tds.get(0).getElementsByTag("img").get(0).attr("src");
                    }
                    String title = td.getElementsByClass("article-headline").get(0).text();
                    String brief = td.getElementsByTag("p").get(0).text();
                    String link = td.getElementsByClass("article-headline").get(0).attr("href");
                    String domain = td.getElementsByTag("span").get(0).text();
                    articles.add(new Article(title, brief, link, imageUrl, domain));
                }
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    callback.onSuccess(articles);
                }
            });
        } catch (final IOException e) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    callback.onFailure(e);
                }
            });
            e.printStackTrace();
        }
    }

}
