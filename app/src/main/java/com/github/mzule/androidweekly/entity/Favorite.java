package com.github.mzule.androidweekly.entity;

import java.io.Serializable;

/**
 * Created by CaoDongping on 3/28/16.
 */
public class Favorite implements Serializable {
    private Article article;
    private long time;

    public Favorite(Article article) {
        this.article = article;
        this.time = System.currentTimeMillis();
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Favorite && article.equals(((Favorite) o).article);
    }

    @Override
    public int hashCode() {
        return article.hashCode();
    }
}
