package com.example.springbootspringclouddemo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolTest extends RecursiveTask<Long> {

    private Long start;

    private Long end;

    public ForkJoinPoolTest(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    public static void main(String[] args) throws Exception {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> forkJoinTask = forkJoinPool.submit(new ForkJoinPoolTest(1L, 100L));
        Long sum = forkJoinTask.get();
        System.out.println(sum);
    }

    @Override
    protected Long compute() {
        Long sum = 0L;
        if (end - start <= 1) {
            for (long i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            ForkJoinPoolTest left = new ForkJoinPoolTest(start, (end + start) / 2);
            ForkJoinPoolTest right = new ForkJoinPoolTest((end + start) / 2 + 1, end);
            left.fork();
            right.fork();
            return left.join() + right.join();
        }
        return sum;
    }
}
