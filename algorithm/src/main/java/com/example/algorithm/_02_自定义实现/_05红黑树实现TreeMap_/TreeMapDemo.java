package com.example.algorithm._02_自定义实现._05红黑树实现TreeMap_;

public class TreeMapDemo {

    public static void main(String[] args) {
       test1();
    }

    public static void test1() {
        Map<String, Integer> map = new TreeMap<>();
        map.put("c", 2);
        map.put("a", 5);
        map.put("b", 6);
        map.put("a", 8);

        map.traversal(new Map.Visitor<String, Integer>() {
            public boolean visit(String key, Integer value) {
                System.out.println(key + "_" + value);
                return false;
            }
        });
    }
}
