package com.example.linklistlru;

public class CacheTest {

    public static void main(String[] args) {
        CacheVersion2 cache = new CacheVersion2(3);
        cache.put("a","a_value");
        cache.put("b","b_value");
        cache.put("c","c_value");
        System.out.println(cache);
        //演示访问一次，改变排序
        String bValue = (String)cache.get("b");
        System.out.println("b的值："+bValue);
        //重新排序
        System.out.println(cache);
        cache.put("d","d_value");
        System.out.println(cache);
    }

}
