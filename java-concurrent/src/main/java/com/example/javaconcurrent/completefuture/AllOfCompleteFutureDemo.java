package com.example.javaconcurrent.completefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * allOf方法的简单使用
 */
public class AllOfCompleteFutureDemo {

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newFixedThreadPool(4);

        CompletableFuture<Void> future1 = CompletableFuture.runAsync(()->{
            System.out.println("future1 runAsync");
        },threadPool).whenComplete((value,throwable)->{
            System.out.println("future1 complete");
        });


        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(()->"Hello",threadPool)
                .whenComplete((value,throwable)->{
                    System.out.println("future2 complete value:"+value);
                });


        CompletableFuture.allOf(future1,future2);

    }
}
