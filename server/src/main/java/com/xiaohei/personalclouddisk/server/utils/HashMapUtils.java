package com.xiaohei.personalclouddisk.server.utils;

import java.util.HashMap;

public class HashMapUtils<K, V> {
    private HashMap<K, V> hashMap;

    public HashMapUtils() {
        hashMap = new HashMap<>();
    }

    /**
     * 返回一个空元素
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> HashMap<K, V> empty() {
        return new HashMap<K, V>();
    }

    public HashMapUtils<K, V> put(K k, V v) {
        hashMap.put(k ,v);
        return this;
    }

    public HashMap<K, V> builder() {
        return this.hashMap;
    }
}
