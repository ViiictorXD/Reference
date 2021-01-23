package com.github.viiictorxd.reference;

public class CacheContext<K, V> {

    private K key;
    private V value;

    public CacheContext(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
