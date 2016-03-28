package com.github.mzule.androidweekly.entity;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by CaoDongping on 3/24/16.
 */
public class Article implements Serializable {
    private String title;
    private String brief;
    private String link;
    private String imageUrl;
    private String domain;

    public Article(String title, String brief, String link, String imageUrl, String domain) {
        this.title = title;
        this.brief = brief;
        this.link = link;
        this.imageUrl = imageUrl;
        this.domain = domain;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public String toString() {
        return Arrays.toString(new String[]{title, "\n", brief, "\n", link, "\n", imageUrl, "\n", domain, "\n", "\n"});
    }

    @Override
    public int hashCode() {
        return link == null ? 0 : link.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Article) {
            Article other = (Article) o;
            return link == null ? other.link == null : link.equals(other.link);
        }
        return false;
    }
}
