package com.example.jvmdemo.memoryleak;

import java.util.ArrayList;
import java.util.List;

/**
 * 静态集合类
 */
public class StaticCollectionMemoryLeakDemo {

    static List list = new ArrayList<>();

    public static void main(String[] args) {
        Object obj = new Object();//局部变量
        list.add(obj);
    }

}
