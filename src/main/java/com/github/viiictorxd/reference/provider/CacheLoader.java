package com.github.viiictorxd.reference.provider;

public abstract class CacheLoader<K, V> {

    public abstract V load(K key);
}
