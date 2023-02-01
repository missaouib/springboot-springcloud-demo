package com.example.jvmdemo.costtime;

import java.util.Random;

/**
 * 方法调用耗时分析测试，使用jvisualvm工具查看
 */
public class MethodCostTimeDemo {

    private static final Random random = new Random();

    public static void main(String[] args) {
        //断点1
        System.out.println("开始执行！");
        method1();
        //断点2
        System.out.println("结束执行！");
    }

    public static void method1() {
        System.out.println("method1!");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        method2();
    }

    public static void method2() {
        System.out.println("method2!");
        try {
            Thread.sleep(1500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        method3();
    }

    public static void method3() {
        System.out.println("method3!");
        try {
            Thread.sleep(getSleepTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        method4();
    }

    public static void method4() {
        System.out.println("method4!");
        try {
            Thread.sleep(getSleepTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        method5();
    }

    public static void method5() {
        System.out.println("method5!");
        try {
            Thread.sleep(getSleepTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        method6();
    }

    public static void method6() {
        System.out.println("method6!");
        try {
            Thread.sleep(getSleepTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        method7();
    }

    public static void method7() {
        System.out.println("method7!");
        try {
            Thread.sleep(getSleepTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        method8();
    }

    public static void method8() {
        System.out.println("method8!");
        try {
            Thread.sleep(getSleepTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        method9();
    }

    public static void method9() {
        System.out.println("method9!");
        try {
            Thread.sleep(getSleepTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        method10();
    }

    public static void method10() {
        System.out.println("method10!");
        try {
            Thread.sleep(getSleepTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static long getSleepTime() {
        int nextInt = random.nextInt(1000);
        if (nextInt <= 10) {
            nextInt = 10;
        }
        return nextInt;
    }

}
