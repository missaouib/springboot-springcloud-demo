package com.example.javaconcurrent.interrupt;

/**
 * Thread.interrupted方法的测试，此方法会重置打断标识为false
 */
public class InterruptedMethodDemo {

    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("doSomething====================");
                    Thread.interrupted();
                    if (this.isInterrupted()) {
                        System.out.println("interrupted isInterrupted true....");
                        break;
                    } else {
                        System.out.println("interrupted isInterrupted false....");
                        break;
                    }
                }
            }
        };
        thread.start();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打断线程
        thread.interrupt();
    }

}
