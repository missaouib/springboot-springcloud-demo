package com.example.javaconcurrent.completefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * compose方法的简单使用：上一个任务的输出结果作为下一个任务的入参
 */
public class ComposeCompleteFutureDemo {

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(()->"Hello world",threadPool);

        CompletableFuture<String> composeFuture = future1.thenCompose(value1 -> {
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                return "compose "+value1;
            }, threadPool);

            return future;
        });

        composeFuture.whenComplete((value,throwable)-> System.out.println(value));

    }

}
