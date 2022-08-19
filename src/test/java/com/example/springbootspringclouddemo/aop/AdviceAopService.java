package com.example.springbootspringclouddemo.aop;

import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AdviceAopService {

    @Transactional
    public PrintValue print(int a,Integer b){
        System.out.println("目标方法执行。。。");
        //throw new RuntimeException("hhhhhhhhhhhhhh");
        return new PrintValue("value");
    }
}
