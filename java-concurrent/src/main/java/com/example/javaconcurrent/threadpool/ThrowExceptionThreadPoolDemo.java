package com.example.javaconcurrent.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程抛出异常不会导致该线程终止，原因是FutureTask会吃掉异常
 */
public class ThrowExceptionThreadPoolDemo {

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<?> future = executorService.submit(() -> {
            System.out.println("执行任务============");
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int i = 1 / 0;
            System.out.println("结束执行任务============");
        });

        Object o = future.get();
    }

}
