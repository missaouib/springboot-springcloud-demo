package com.example.ehcache;

import com.example.ehcache.model.ProductInfo;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.util.Arrays;

public class EhcacheApiTest {

    public static void main(String[] args) {
        //定义ehcache的配置文件
        String path = "E:\\developer\\IdeaProjects\\springboot-springcloud-demo\\cache-demo\\ehcache\\src\\main\\resources\\ehcache.xml";
        //根据ehcache.xml创建cacheManager，管理多个cache,user_info，item_cache...
        CacheManager cacheManager = CacheManager.create(path);
        //getCacheNames()：获取到cacheManager管理的所有cache
        String[] cacheNames = cacheManager.getCacheNames();
        System.out.println("获取到的cache的名字：" + Arrays.toString(cacheNames));
        //通过cache的名字(我们指定的)获取具体的cache
        Cache productCache = cacheManager.getCache("local");

        ProductInfo productInfo = new ProductInfo();
        productInfo.setId(123L);
        //往userCache中放入一个user，参数：key 对象
        Element element = new Element(productInfo.getId(),productInfo);
        productCache.put(element);

        //通过key取出 缓存的对象
        Element resultEle = productCache.get(123L);
        System.out.println("获取到的resultEle：" + resultEle);
        System.out.println("获取到的resultEle的Value：" + resultEle.getObjectValue());
    }
}
