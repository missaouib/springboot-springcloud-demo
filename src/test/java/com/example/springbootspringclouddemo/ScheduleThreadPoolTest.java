package com.example.springbootspringclouddemo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ScheduleThreadPoolTest {

    public static void main(String[] args) {
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(10);
        threadPool.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    log.info("Thread:{},data:{}", Thread.currentThread().getName(), "执行任务。。。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 3, TimeUnit.SECONDS);
    }
}
