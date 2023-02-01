package com.example.jvmdemo.gc_reference;

import java.lang.ref.SoftReference;

/**
 * 测试软引用在内存不足时会被回收
 * jvm参数：-Xmx5m -Xms5m -XX:+PrintGCDetails
 */
public class SoftReferenceNotEnoughGCDemo {

    public static void main(String[] args) {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;
        //触发full gc
        System.gc();

        try {
            byte[] bytes = new byte[30*1024*1024];
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            System.out.println("===========================================");
            System.out.println("o1="+o1);
            System.out.println("softReference.get()="+softReference.get());
            System.out.println("===========================================");
        }

    }

}
