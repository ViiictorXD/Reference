package com.fastcache;

public abstract class CacheLoader<K, V> {

    public abstract V load(K key);
}
