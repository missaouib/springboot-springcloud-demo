package com.example.javaconcurrent.interrupt;

/**
 * 线程自己打断自己
 */
public class SelfInterruptDemo {

    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("doSomething====================");
                    this.interrupt();
                    if (this.isInterrupted()) {
                        System.out.println("self interrupt....");
                        break;
                    } else {
                        System.out.println("self interrupt fail....");
                        break;
                    }
                }
            }
        };
        thread.start();
    }
}
