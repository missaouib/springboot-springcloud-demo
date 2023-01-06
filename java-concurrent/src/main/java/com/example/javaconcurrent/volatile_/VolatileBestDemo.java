package com.example.javaconcurrent.volatile_;

public class VolatileBestDemo {

    private static volatile int INIT_VALUE = 0;

    private static final int MAX_VALUE = 5;

    public static void main(String[] args) {
        new Thread(()->{
            int localValue = INIT_VALUE;
            while (localValue < MAX_VALUE) {
                if (localValue != INIT_VALUE) {
                    System.out.printf("The value updated to [%d]\n",INIT_VALUE);
                    localValue = INIT_VALUE;
                }
            }
        },"READER").start();

        new Thread(()->{
            int localValue = INIT_VALUE;
            while (INIT_VALUE < MAX_VALUE) {
                System.out.printf("Update the value to [%d]\n",++localValue);
                INIT_VALUE = localValue;
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"WRITER").start();
    }
}
