package com.github.mzule.androidweekly.entity;

import java.io.Serializable;

/**
 * Created by CaoDongping on 3/25/16.
 */
public class Issue implements Serializable {
    private String name;
    private boolean active;

    public Issue(String name) {
        this.name = name;
    }

    public Issue(String name, boolean active) {
        this.name = name;
        this.active = active;
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
