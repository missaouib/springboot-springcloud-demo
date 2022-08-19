package com.example.springbootspringclouddemo;

import io.netty.buffer.Unpooled;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

@Slf4j
public class ABATest {

    public static void main(String[] args) {
        AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(100, 1);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stampedReference.compareAndSet(100, 101, stampedReference.getStamp(), stampedReference.getStamp() + 1);
                stampedReference.compareAndSet(101, 100, stampedReference.getStamp(), stampedReference.getStamp() + 1);
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int stamp = stampedReference.getStamp();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //此处不能写成boolean flag = stampedReference.compareAndSet(100, 101, stampedReference.getStamp(),stampedReference.getStamp()+1);
                boolean flag = stampedReference.compareAndSet(100, 101, stamp, stamp + 1);
                //此处不能写成
                log.info("flag={}", flag);//false
            }
        }, "t2");
        t1.start();
        t2.start();
    }
}
