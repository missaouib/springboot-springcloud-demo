package com.example.javaconcurrent.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class ObtainUnsafeDemo {

    public static void main(String[] args) throws Exception {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe)theUnsafe.get(null);
        System.out.println(unsafe);
    }
}
