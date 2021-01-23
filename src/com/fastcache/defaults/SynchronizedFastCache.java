package com.fastcache.defaults;

import com.fastcache.CacheContext;
import com.fastcache.CacheLoader;

import java.lang.ref.SoftReference;

public class SynchronizedFastCache<K, V> extends FastCache<K, V> {

    public SynchronizedFastCache(int maximumSize) {
        super(maximumSize);
    }

    public SynchronizedFastCache(int maximumSize, long expireAfterWrite) {
        super(maximumSize, expireAfterWrite);
    }

    public SynchronizedFastCache(int maximumSize, CacheLoader<K, V> cacheLoader) {
        super(maximumSize, cacheLoader);
    }

    public SynchronizedFastCache(int maximumSize, long expireAfterWrite, CacheLoader<K, V> cacheLoader) {
        super(maximumSize, expireAfterWrite, cacheLoader);
    }

    @Override
    public synchronized V fetch(K key) {
        return super.fetch(key);
    }

    @Override
    public synchronized V fetchOrDefault(K key, V value) {
        return super.fetchOrDefault(key, value);
    }

    @Override
    public synchronized void put(K key, V value) {
        super.put(key, value);
    }

    @Override
    public synchronized void putAll(SoftReference<CacheContext<K, V>>[] references) {
        super.putAll(references);
    }

    @Override
    public synchronized void clear() {
        super.clear();
    }

    @Override
    public synchronized void remove(K key) {
        super.remove(key);
    }

    @Override
    public synchronized boolean contains(K key) {
        return super.contains(key);
    }
}
