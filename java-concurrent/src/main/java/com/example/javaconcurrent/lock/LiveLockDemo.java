package com.example.javaconcurrent.lock;

import lombok.extern.slf4j.Slf4j;

/**
 * 活锁
 */
@Slf4j(topic = "c.TestLiveLock")
public class LiveLockDemo {

    static volatile int count = 10;

    public static void main(String[] args) {

        new Thread(()->{
            //期望减到0退出循环
           while (count > 0) {
               try {
                   Thread.sleep(200L);
                   count--;
                   log.debug("count:{}",count);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        },"t1").start();


        new Thread(()->{
            //期望超过20退出循环
            while (count < 20) {
                try {
                    Thread.sleep(200L);
                    count++;
                    log.debug("count:{}",count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t2").start();

    }
}
