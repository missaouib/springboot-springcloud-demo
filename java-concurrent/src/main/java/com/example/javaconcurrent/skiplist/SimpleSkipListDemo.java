package com.example.javaconcurrent.skiplist;

import java.util.Random;

public class SimpleSkipListDemo {

    public static void main(String[] args) {
        SimpleSkipList skipList = new SimpleSkipList();
        skipList.add(10);
        skipList.dumpSkipList();
        skipList.add(1);
        skipList.dumpSkipList();
        Random random = new Random();
        for (int i=0;i<10;i++) {
            skipList.add(random.nextInt(1000));
        }
        skipList.dumpSkipList();
    }
}
