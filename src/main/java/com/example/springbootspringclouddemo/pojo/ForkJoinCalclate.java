package com.example.springbootspringclouddemo.pojo;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinCalclate extends RecursiveTask<Long> {

    private long start;

    private long end;

    public static final long THRESHOLD = 25L;

    public ForkJoinCalclate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;
        if (length <= THRESHOLD) {
            long sum = 0L;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (start + end) / 2;
            ForkJoinCalclate left = new ForkJoinCalclate(start, middle);
            left.fork();//拆分子任务，同时压入线程队列
            ForkJoinCalclate right = new ForkJoinCalclate(middle + 1, end);
            right.fork();
            return left.join() + right.join();
        }
    }

    public static void main(String[] args) {
        Instant start = Instant.now();

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalclate(0, 100);
        Long sum = pool.invoke(task);
        System.out.println(sum);

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
    }
}
