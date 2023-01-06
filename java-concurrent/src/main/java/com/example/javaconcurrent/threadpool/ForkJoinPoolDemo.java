package com.example.javaconcurrent.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoinPool测试：t1.fork()会新建线程执行任务，t1.join()阻塞当前任务
 */
public class ForkJoinPoolDemo {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(4);
        pool.invoke(new MyTask(5));
    }

    /**
     * 1~n的整数求和
     */
    @Slf4j(topic = "MyTask")
    static class MyTask extends RecursiveTask<Integer> {

        private int n;

        public MyTask(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            log.info("{} compute,n:{}",Thread.currentThread().getName(),n);
            //终止条件
            if (n == 1) {
                return 1;
            }
            MyTask t1 = new MyTask(n-1);
            log.info("{} 开始fork,n:{}",Thread.currentThread().getName(),n);
            //让一个线程去执行此任务
            t1.fork();
            log.info("{} 结束fork,n:{}",Thread.currentThread().getName(),n);

            int result = n + t1.join();
            log.info("{} 获取结果,result:{},n:{}",Thread.currentThread().getName(),result,n);

            return result;
        }
    }
}
