package com.example.guava;

import com.example.guava.model.ProductInfo;
import com.google.common.cache.*;

import java.util.concurrent.TimeUnit;

public class LoadingCacheTest {

    public static void main(String[] args) throws Exception {
        /**
         * 1.com.google.common.cache.LocalCache.LocalLoadingCache
         * 特点：缓存中获取不到值，会根据指定的loader进行加载后自动放入缓存
         * 2.com.google.common.cache.LocalCache.LocalManualCache
         * 特点：类似ehcache
         */
        /**
         * 通过字符串方式指定concurrencyLevel和initialCapacity
         * concurrencyLevel：指定并发级别
         * initialCapacity：初始化大小，配合concurrencyLevel做分段锁，提高性能
         */
        String spec = "concurrencyLevel=8,initialCapacity=60";
        LoadingCache<Long, ProductInfo> loadingCache = CacheBuilder.from(CacheBuilderSpec.parse(spec))
                //缓存中最多可以放多少元素
                .maximumSize(10)
                //从写入开始计算，3s以后过期，会被清除
                .expireAfterWrite(3, TimeUnit.SECONDS)
                //统计命中率,每次执行完缓存组件的方法后都要先调用loadingCache.stats()再获取统计信息
                .recordStats()
                .softValues()
                //缓存中的元素被驱逐出去后自动回调到这里
                .removalListener(new RemovalListener<Long, ProductInfo>() {
                    @Override
                    public void onRemoval(RemovalNotification<Long, ProductInfo> notification) {
                        Long key = notification.getKey();
                        RemovalCause cause = notification.getCause();
                        System.out.println("key：" + key+",被移出缓存，原因："+cause);
                    }
                })
                //缓存中获取不到值，会回调到这里
                .build(new CacheLoader<Long, ProductInfo>() {
                    @Override
                    public ProductInfo load(Long key) throws Exception {
                        //key：将来loadingCache.get(key)获取不到传来的key
                        //可以在这里进行数据的加载
                        System.out.println("去存储中加载");
                        ProductInfo info = new ProductInfo();
                        info.setName("去存储中加载");
                        return info;
                    }
                });

        for (long i = 0; i < 20; i++) {
            //get方法抛异常  loadingCache.get(i);
            ProductInfo info = loadingCache.getUnchecked(999L);
            ProductInfo info2 = loadingCache.getUnchecked(999L);
            System.out.println(info);
            System.out.println(info2);
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("统计信息"+loadingCache.stats().toString());
    }
}
