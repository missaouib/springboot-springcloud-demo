package com.example.ehcache.service;

import com.example.ehcache.model.ProductInfo;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("cacheService")
public class CacheService {
   
    public static final String CACHE_NAME = "local";
    
    @Cacheable(value = CACHE_NAME, key = "'key_'+#id")
    public ProductInfo findById(Long id){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setId(id);
        return productInfo;
    }
   
    @CachePut(value = CACHE_NAME, key = "'key_'+#productInfo.getId()")
    public ProductInfo saveProductInfo(ProductInfo productInfo) {
      return productInfo;
    }
     
}