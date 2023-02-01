package com.example.javaconcurrent.print_abc;

import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程顺序打印ABC
 */
public class PrintABCThreadDemo {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition a = lock.newCondition();
        Condition b = lock.newCondition();
        Condition c = lock.newCondition();

        new Thread("A") {
            @Override
            public void run() {
                lock.lock();
                for (int i= 0;i < 5;i++) {
                    try {
                        a.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print("A");
                    b.signal();
                }
                lock.unlock();
            }
        }.start();

        new Thread("B") {
            @Override
            public void run() {
                lock.lock();
                for (int i= 0;i < 5;i++) {
                    try {
                        b.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print("B");
                    c.signal();
                }
                lock.unlock();
            }
        }.start();

        new Thread("C") {
            @Override
            public void run() {
                lock.lock();
                for (int i= 0;i < 5;i++) {
                    try {
                        c.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print("C");
                    a.signal();
                }
                lock.unlock();
            }
        }.start();


        lock.lock();
        a.signal();
        lock.unlock();

        while (Thread.activeCount()>2) {

        }

        System.out.println("结束。。。");
    }

}
