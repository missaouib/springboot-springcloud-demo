package com.example.linkhashmapsoftreference;

import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
import java.util.Map;

public class CacheVersion1 implements Cache {
    //缓存容量
    private int capacity;
    //通过组合关系持有一个内部的真正缓存对象
    private InternalCache internalCache;

    public CacheVersion1(int capacity) {
        this.capacity = capacity;
        internalCache = new InternalCache(capacity);
    }

    private static class InternalCache extends LinkedHashMap<Object,Object> {
        private int capacity;
        public InternalCache(int capacity) {
            super(16,0.75f,true);
            this.capacity = capacity;}
        /**
         *在java.util.LinkedHashMap#afterNodeInsertion(boolean)回调
         * @param eldest
         * @return true：清除排在最前面的元素，false不清除
         */
        @Override
        protected boolean removeEldestEntry(Map.Entry<Object, Object> eldest) {
            return size() > capacity;
        }
    }
    @Override
    public void put(Object key, Object value) {
        SoftReference<Object> softReference = new SoftReference<>(value);
        internalCache.put(key,softReference);
    }

    @Override
    public void remove(Object key, Object value) {
        internalCache.remove(key,value);
    }

    @Override
    public void clear() {
        internalCache.clear();
    }

    @Override
    public Object get(Object key) {
        Object o = internalCache.get(key);
        if(o == null){
            return null;
        }
        SoftReference<Object> softReference = (SoftReference<Object>) o;
        //返回引用的真实对象
        return softReference.get();
    }

    @Override
    public int size() {
        return internalCache.size();
    }

    @Override
    public String toString() {
        return "CacheVersion1{" +
                "capacity=" + capacity +
                ", internalCache=" + internalCache +
                '}';
    }
}
