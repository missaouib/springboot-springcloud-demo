package com.example.jvmdemo.gc_reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * 测试虚引用垃圾回收
 */
public class PhantomReferenceGCDemo {

    public static void main(String[] args) {
        Object o1 = new Object();
        //垃圾回收时会将weakReference对象放入队列中
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o1,queue);
        System.out.println("o1="+o1);
        System.out.println("phantomReference.get()="+phantomReference.get());
        System.out.println("queue.poll()="+queue.poll());

        o1 = null;
        System.gc();
        System.out.println("===========================================");

        System.out.println("o1="+o1);
        System.out.println("phantomReference.get()="+phantomReference.get());
        System.out.println("queue.poll()="+queue.poll());

    }

}
