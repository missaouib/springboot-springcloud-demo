package com.example.javaconcurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 对于缓存线程池，如果线程池中有空闲的线程，不会新建非core线程，而是使用空闲的线程执行提交的任务
 */
public class CachedThreadPoolExecutorDemo {

    public static void main(String[] args) throws Exception {
        ExecutorService cachedPool = Executors.newCachedThreadPool();
        cachedPool.submit(()->{
            System.out.println(Thread.currentThread().getName()+"执行任务1==============");
        });
        Thread.sleep(1000L);
        cachedPool.submit(()->{
            System.out.println(Thread.currentThread().getName()+"执行任务2==============");
        });

        Thread.sleep(50*1000L);
        cachedPool.shutdown();
    }

}
