package com.github.mzule.androidweekly.api;


import android.os.Handler;

import com.github.mzule.androidweekly.dao.IssueListKeeper;
import com.github.mzule.androidweekly.entity.Article;
import com.github.mzule.androidweekly.entity.Issue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mzule on 3/16/16.
 */
public class ArticleApi {
    //TODO new thread to threadhandler
    private Handler handler = new Handler();

    public void getPage(final String issue, final ApiCallback<List<Object>> callback) {
        new Thread() {
            @Override
            public void run() {
                try {
                    postSuccess(doGetPage(issue), callback);
                } catch (final Exception e) {
                    postError(e, callback);
                }

            }
        }.start();
    }

    public void getArchive(final ApiCallback<List<Issue>> callback) {
        new Thread() {
            @Override
            public void run() {
                try {
                    List<Issue> issues = IssueListKeeper.read();
                    if (issues != null && !issues.isEmpty()) {
                        postSuccess(new Response<>(issues, true), callback);
                    }
                    postSuccess(doGetArchive(), callback);
                } catch (Exception e) {
                    postError(e, callback);
                }
            }
        }.start();
    }

    private Response<List<Issue>> doGetArchive() throws Exception {
        Document doc = Jsoup.parse(new URL("http://androidweekly.net/archive"), 30000);
        Elements lis = doc.getElementsByClass("archive-list").get(0).getElementsByTag("li");
        List<Issue> issues = new ArrayList<>();
        for (Element li : lis) {
            String date = li.getElementsByTag("span").get(0).text();
            String name = li.getElementsByTag("h3").text().split("\\|")[0];
            String url = li.getElementsByTag("a").attr("href");
            issues.add(new Issue(name, url, date));
        }
        IssueListKeeper.save(issues);
        return new Response<>(issues, false);
    }

    private Response<List<Object>> doGetPage(String issue) throws Exception {
        String url = "http://androidweekly.net";
        if (issue != null) {
            url += issue;
        }
        Document doc = Jsoup.parse(new URL(url), 30000);
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
                String domain = td.getElementsByTag("span").get(0).text().replace("(", "").replace(")", "");
                articles.add(new Article(title, brief, link, imageUrl, domain));
            }
        }
        return new Response<>(articles, false);
    }

    private <T> void postSuccess(final Response<T> result, final ApiCallback<T> callback) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(result.data, result.fromCache);
            }
        });
    }

    private <T> void postError(final Exception e, final ApiCallback<T> callback) {
        e.printStackTrace();
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onFailure(e);
            }
        });
    }

    static class Response<T> {
        public T data;
        public boolean fromCache;

        public Response(T data, boolean fromCache) {
            this.data = data;
            this.fromCache = fromCache;
        }
    }
}
