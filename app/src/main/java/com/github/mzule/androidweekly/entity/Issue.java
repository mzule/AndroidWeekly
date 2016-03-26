package com.github.mzule.androidweekly.entity;

import java.io.Serializable;

/**
 * Created by CaoDongping on 3/25/16.
 */
public class Issue implements Serializable {
    private String date;
    private String name;
    private String url;
    private boolean active;

    public Issue(String name) {
        this.name = name;
    }

    public Issue(String name, boolean active) {
        this.name = name;
        this.active = active;
    }

    public Issue(String name, String url, String date) {
        this.name = name;
        this.url = url;
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
