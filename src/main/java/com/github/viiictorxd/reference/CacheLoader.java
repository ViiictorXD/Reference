package com.github.viiictorxd.reference;

public abstract class CacheLoader<K, V> {

    public abstract V load(K key);
}
