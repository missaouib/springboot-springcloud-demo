package com.example.jvmdemo.gc_reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 测试弱引用垃圾回收
 * jvm参数设置：-XX:+PrintGCDetails
 */
public class WeakReferenceGCDemo {

    public static void main(String[] args) {
        Object o1 = new Object();
        //垃圾回收时会将weakReference对象放入队列中
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(o1,queue);
        System.out.println("o1="+o1);
        System.out.println("weakReference.get()="+weakReference.get());
        System.out.println("queue.poll()="+queue.poll());

        o1 = null;
        System.gc();
        System.out.println("===========================================");

        System.out.println("o1="+o1);
        System.out.println("weakReference.get()="+weakReference.get());
        System.out.println("queue.poll()="+queue.poll());
    }

}
