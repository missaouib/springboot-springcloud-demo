package com.example.javaconcurrent.interrupt;

/**
 * interrupt方法和Thread.sleep方法的测试，Thread.sleep会重置打断标识为false
 */
public class InterruptMethodDemo {

    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println("doSomething====================");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        if (this.isInterrupted()) {
                            System.out.println("Thread.sleep isInterrupted true....");
                            break;
                        } else {
                            System.out.println("Thread.sleep isInterrupted false....");
                            break;
                        }
                    }

                }
            }
        };
        thread.start();
        //打断线程
        thread.interrupt();
    }

}
