package com.example.algorithm._02_自定义实现._07红黑树和双向链表实现LinkedHashMap_;

public class LinkedHashMapDemo {

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        LinkedHashMap<Object, Integer> map = new LinkedHashMap<>();
        map.put("jack", 1);
        map.put("rose", 2);
        map.put("jim", 3);
        map.put("jake", 4);
        map.remove("jack");
        map.remove("jim");
        for (int i = 1; i <= 10; i++) {
            map.put("test" + i, i);
        }

        //打印
        map.print();

        map.traversal(new Map.Visitor<Object, Integer>() {
            public boolean visit(Object key, Integer value) {
                System.out.println(key + "_" + value);
                return false;
            }
        });
    }
}
