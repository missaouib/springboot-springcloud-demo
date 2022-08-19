package com.example.springbootspringclouddemo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheDemo<K,V> extends LinkedHashMap<K,V> {

    private int capacity;

    public LRUCacheDemo(int capacity) {
        //true表示访问顺序：[4,3,5]
        //false表示插入顺序：[3,4,5]
        super(capacity,0.75F,true);
        this.capacity = capacity;
    }

    /**
     * 重写此方法
     * @param eldest
     * @return
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return super.size() > this.capacity;
    }

    public static void main(String[] args) {
        LRUCacheDemo lruCacheDemo = new LRUCacheDemo(3);

        lruCacheDemo.put(1,1);
        lruCacheDemo.put(2,2);
        lruCacheDemo.put(3,3);
        System.out.println(lruCacheDemo.keySet());

        lruCacheDemo.put(4,1);
        System.out.println(lruCacheDemo.keySet());

        lruCacheDemo.put(3,1);
        System.out.println(lruCacheDemo.keySet());
        lruCacheDemo.put(3,1);
        System.out.println(lruCacheDemo.keySet());
        lruCacheDemo.put(3,1);
        System.out.println(lruCacheDemo.keySet());
        lruCacheDemo.put(5,1);
        System.out.println(lruCacheDemo.keySet());
    }
}
