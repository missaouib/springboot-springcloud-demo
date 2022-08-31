package com.example.mockito.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {

    private String shopMsg = "shop...";

    @Autowired
    private GoodsService goodsService;

    public ShopService() {
    }

    public ShopService(String shopMsg) {
        this.shopMsg = shopMsg;
    }

    public void shop() {
        System.out.println(shopMsg);
    }

}
