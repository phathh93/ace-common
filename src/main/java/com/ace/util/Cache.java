package com.ace.util;

import java.util.Date;
import java.util.List;

public class Cache<T> {
    // TODO update to at least 5 minutes
    private static final long COOLDOWN = 1000 * 60 * 1;

    protected List<T> data;
    protected Date lastUpdated;

    protected long cooldown;

    public Cache(List<T> data) {
        this.data = data;
        this.lastUpdated = new Date();
        this.cooldown = COOLDOWN;
    }

    public Cache(List<T> data, long cooldown) {
        this.data = data;
        this.lastUpdated = new Date();
        this.cooldown = cooldown;
    }

    public boolean isExpired() {
        return new Date().getTime() - lastUpdated.getTime() > COOLDOWN;
    }

    public List<T> getData() {
        return data;
    }

    public void renew(List<T> data) {
        this.data = data;
        this.lastUpdated = new Date();
    }

}