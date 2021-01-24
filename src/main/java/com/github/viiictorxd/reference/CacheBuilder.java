package com.github.viiictorxd.reference;

import com.github.viiictorxd.reference.impl.FastCache;

import java.util.concurrent.TimeUnit;

public class CacheBuilder<K, V> {

    private int maximumSize = 255;
    private long expireAfter;
    private CacheLoader<K, V> cacheLoader;

    public CacheBuilder<K, V> maximumSize(int maximumSize) {
        this.maximumSize = maximumSize;
        return this;
    }

    public CacheBuilder<K, V> expireAfter(int time, TimeUnit unit) {
        this.expireAfter = unit.toMillis(time);
        return this;
    }

    public CacheBuilder<K, V> applyLoader(CacheLoader<K, V> cacheLoader) {
        this.cacheLoader = cacheLoader;
        return this;
    }

    public Cache<K, V> build() {
        return new FastCache<>(maximumSize, expireAfter, cacheLoader);
    }
}
