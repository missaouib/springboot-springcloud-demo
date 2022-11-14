package com.example;

public class PerformanTest {

    public static void main(String[] args) throws Exception {
        Thread.sleep(10*1000L);
        a();
        System.out.println("正在等待。。。。");
        System.in.read();
    }

    public static void a() throws InterruptedException {
        System.out.println("a==============");
        Thread.sleep(500L);
        b();
    }

    public static void b() throws InterruptedException {
        System.out.println("b==============");
        Thread.sleep(1000L);
        c();
    }

    public static void c() throws InterruptedException {
        System.out.println("c==============");
        Thread.sleep(1500L);
        d();
    }

    public static void d() throws InterruptedException {
        System.out.println("d==============");
        Thread.sleep(2000L);
    }
}
