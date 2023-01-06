package com.example.javaconcurrent.synchronized_;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * 引入如下依赖：
 * <!-- https://mvnrepository.com/artifact/org.openjdk.jol/jol-core -->
 * <dependency>
 *     <groupId>org.openjdk.jol</groupId>
 *     <artifactId>jol-core</artifactId>
 *     <version>0.16</version>
 *     <scope>provided</scope>
 * </dependency>
 */
@Slf4j(topic = "c.TestBiased")
public class SynchronizedBiasedLockDemo {

    public static void main(String[] args) throws InterruptedException {
        Dog d = new Dog();
        log.debug(ClassLayout.parseInstance(d).toPrintable());

        //（偏向锁默认延迟4秒）
        Thread.sleep(5000L);

        log.debug(ClassLayout.parseInstance(d).toPrintable());

        synchronized (d) {
            log.debug(ClassLayout.parseInstance(d).toPrintable());
        }

        log.debug(ClassLayout.parseInstance(d).toPrintable());
    }


    static class Dog {

    }
}
