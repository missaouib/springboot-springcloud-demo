package com.example.javaconcurrent.completefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * runAfterBoth方法的简单使用
 */
public class RunAfterBothCompleteFutureDemo {

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newFixedThreadPool(4);

        CompletableFuture<Void> future1 = CompletableFuture.supplyAsync(Object::new,threadPool)
                .thenAcceptAsync(obj -> {
                    try {
                        System.out.println("Obj=====start");
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("Obj=====end");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                },threadPool);

        CompletableFuture<Void> future2 = CompletableFuture.supplyAsync(()->"Hello",threadPool)
                .thenAcceptAsync(obj -> {
                    try {
                        System.out.println("String=====start");
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("String=====end");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                },threadPool);

        future1.runAfterBoth(future2,()-> System.out.println("Finished"));

    }

}
