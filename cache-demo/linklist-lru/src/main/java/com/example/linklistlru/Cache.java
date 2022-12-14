package com.example.linklistlru;

/**
 * 定义基本的缓存功能接口
 */
public interface Cache {
    void put(Object key,Object value);
    void remove(Object key,Object value);
    void clear();
    Object get(Object key);
    int size();
}
