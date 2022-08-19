package com.example.aopaspectorder;


public class AdviceAopService {

    public PrintValue print(int a, Integer b){
        System.out.println("目标方法执行。。。");
        //throw new RuntimeException("hhhhhhhhhhhhhh");
        return new PrintValue("value");
    }
}
