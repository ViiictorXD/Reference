package com.github.viiictorxd.reference.impl;

import com.github.viiictorxd.reference.Cache;
import com.github.viiictorxd.reference.CacheContext;
import com.github.viiictorxd.reference.CacheLoader;

import java.lang.ref.SoftReference;
import java.util.*;

public class FastCache<K, V> implements Cache<K, V> {

    private final int maximumSize;
    private final long expireAfter;
    private SoftReference<CacheContext<K, V>>[] references;
    private SoftReference<CacheContext<K, Long>>[] referencesTime;

    private CacheLoader<K, V> cacheLoader;

    public FastCache(int maximumSize) {
        this(maximumSize, 0L);
    }

    public FastCache(int maximumSize, long expireAfter) {
        this(maximumSize, expireAfter, null);
    }

    public FastCache(int maximumSize, CacheLoader<K, V> cacheLoader) {
        this(maximumSize, 0L, cacheLoader);
    }

    public FastCache(int maximumSize, long expireAfter, CacheLoader<K, V> cacheLoader) {
        this.maximumSize = maximumSize;
        this.references = new SoftReference[maximumSize];
        this.referencesTime = new SoftReference[maximumSize];
        this.expireAfter = expireAfter;
        this.cacheLoader = cacheLoader;
    }

    public void setCacheLoader(CacheLoader<K, V> cacheLoader) {
        this.cacheLoader = cacheLoader;
    }

    @Override
    public V fetch(K key) {
        SoftReference<CacheContext<K, V>> reference = references[hashOf(key)];
        if (reference != null) {
            CacheContext<K, V> cacheContext = reference.get();
            if (cacheContext != null
                    && cacheContext.getKey() != null
                    && cacheContext.getValue() != null
                    && cacheContext.getKey().equals(key)) {
                SoftReference<CacheContext<K, Long>> timeReference = referencesTime[hashOf(key)];
                if (Objects.requireNonNull(timeReference.get()).getValue()
                        < System.currentTimeMillis()) {
                    remove(key);
                    return null;
                }

                return cacheContext.getValue();
            }
        }

        if (cacheLoader != null) {
            V value = cacheLoader.load(key);
            if (value == null)
                return null;

            put(key, value);

            return value;
        }

        return null;
    }

    @Override
    public V fetchOrDefault(K key, V value) {
        V fetched = fetch(key);
        if (fetched != null) return fetched;
        else return value;
    }

    @Override
    public Collection<CacheContext<K, V>> values() {
        List<CacheContext<K, V>> values = new ArrayList<>();

        for (SoftReference<CacheContext<K, V>> reference : references) {
            if (reference == null)
                continue;

            CacheContext<K, V> context = reference
                    .get();
            if (context == null)
                continue;

            values.add(context);
        }

        return values;
    }

    @Override
    public int size() {
        return values().size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void put(K key, V value) {
        references[hashOf(key)] = new SoftReference<>(new CacheContext<K, V>(key, value));
        referencesTime[hashOf(key)] = new SoftReference<>(new CacheContext<K, Long>(key, System.currentTimeMillis() + expireAfter));
    }

    @Override
    public void putAll(SoftReference<CacheContext<K, V>>[] references) {
        this.references = references;
    }

    @Override
    public void clear() {
        Arrays.fill(references, null);
    }

    @Override
    public void remove(K key) {
        references[hashOf(key)] = null;
        referencesTime[hashOf(key)] = null;
    }

    @Override
    public boolean contains(K key) {
        return fetch(key) != null;
    }

    private int hashOf(Object object) {
        long hash = (long) object.hashCode() + Integer.MAX_VALUE;
        hash = hash >> 16 ^ hash;

        return ((int) hash & 0xffff) % maximumSize;
    }
}
