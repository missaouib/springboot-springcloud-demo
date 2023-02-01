package com.example.algorithm._02_自定义实现._10跳表_;

public class SkipListDemo {

    public static void main(String[] args) {
        SkipList<Integer, Integer> list = new SkipList<>();
        for (int i = 0; i < 30; i++) {
            list.put(i, i + 10);
        }
		System.out.println(list);
    }

}
