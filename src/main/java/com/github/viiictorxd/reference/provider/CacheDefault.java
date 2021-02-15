package com.github.viiictorxd.reference.provider;

public abstract class CacheDefault<K, V> {

    public abstract V load(K key);
}
