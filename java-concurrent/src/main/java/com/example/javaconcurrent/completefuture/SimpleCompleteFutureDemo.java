package com.example.javaconcurrent.completefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 简单使用：自定义线程池，否则使用ForkJoinPool.commonPool()，并且ForkJoinPool.commonPool()创建的线程都是守护线程
 */
public class SimpleCompleteFutureDemo {

    public static void main(String[] args) throws Exception {

        ExecutorService threadPool = Executors.newFixedThreadPool(1);

        CompletableFuture.runAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },threadPool).whenComplete((value,throwable)-> System.out.println("DONE"));
        System.out.println("==============i am not blocked================");

        Thread.currentThread().join();
    }
}
