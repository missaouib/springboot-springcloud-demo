package com.example.algorithm._02_自定义实现._02链表_._单向链表_;

import com.example.algorithm._02_自定义实现.List;

public class LinkedListDemo {

    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        list.add(20);
        list.add(0,10);
        list.add(30);
        list.add(list.size(),40);

        list.remove(1);
        System.out.println(list);
    }

}
