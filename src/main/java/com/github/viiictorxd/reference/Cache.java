package com.github.viiictorxd.reference;

import java.lang.ref.SoftReference;
import java.util.Collection;

public interface Cache<K, V> {

    V fetch(K key);

    V fetchOrDefault(K key, V value);

    Collection<CacheContext<K, V>> values();

    int size();

    boolean isEmpty();

    void put(K key, V value);

    void putAll(SoftReference<CacheContext<K, V>>[] references);

    void remove(K key);

    void clear();

    boolean contains(K key);
}
