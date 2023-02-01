package com.example.javaconcurrent.threadstate;

/**
 * 测试线程的BLOCKED状态
 */
public class ThreadStateDemo {

    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                synchronized (ThreadStateDemo.class) {
                    try {
                        System.out.println("任务1加锁=====================");
                        Thread.sleep(10*1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(){
            @Override
            public void run() {
                synchronized (ThreadStateDemo.class) {
                    try {
                        System.out.println("任务2加锁=====================");
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

}
