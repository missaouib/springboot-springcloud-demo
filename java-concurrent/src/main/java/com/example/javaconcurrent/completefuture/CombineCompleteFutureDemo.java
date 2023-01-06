package com.example.javaconcurrent.completefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * combine方法的简单使用：组合结果
 */
public class CombineCompleteFutureDemo {

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(()->"Hello",threadPool);

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(()->"world",threadPool);

        future1.thenCombine(future2,(value1,value2)->{
            return value1+" "+value2;
        }).whenComplete((value,throwable)->{
            System.out.println("combine value:"+value);
        });

    }

}
