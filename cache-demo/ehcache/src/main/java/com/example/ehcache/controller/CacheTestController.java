package com.example.ehcache.controller;

import com.example.ehcache.service.CacheService;
import com.example.ehcache.model.ProductInfo ;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class CacheTestController {

  @Resource
  private CacheService cacheService;
  
  @RequestMapping("/testPutCache")
  @ResponseBody
  public void testPutCache(ProductInfo productInfo) {
    System.out.println(productInfo.getId() + ":" + productInfo.getName());  
    cacheService.saveProductInfo(productInfo);
  }
  
  @RequestMapping("/testGetCache")
  @ResponseBody
  public ProductInfo testGetCache(Long id) {
    ProductInfo productInfo = cacheService.findById(id);
    System.out.println(productInfo.getId() + ":" + productInfo.getName()); 
    return productInfo;
  }
  
}