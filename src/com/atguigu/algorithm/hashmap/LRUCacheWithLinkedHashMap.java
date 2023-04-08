package com.atguigu.algorithm.hashmap;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheWithLinkedHashMap extends LinkedHashMap<Integer, Integer> {
    // 定义缓存容量
    private int capacity;

    public LRUCacheWithLinkedHashMap(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    // 访问数据的get方法
    public int get(int key){
        if (super.get(key) == null) return -1;
        return super.get(key);
    }

    // put方法
    public void put(int key, int value){
        super.put(key, value);
    }

    // 重写是否删除元素的方法
    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }

    public static void main(String[] args) {
        LRUCacheWithLinkedHashMap lRUCache = new LRUCacheWithLinkedHashMap(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println(lRUCache.get(1));   // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(1));     // 返回 -1 (未找到)
        System.out.println(lRUCache.get(3));     // 返回 3
        System.out.println(lRUCache.get(4));     // 返回 4
    }
}
