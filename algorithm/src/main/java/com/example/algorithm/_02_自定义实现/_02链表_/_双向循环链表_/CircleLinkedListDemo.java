package com.example.algorithm._02_自定义实现._02链表_._双向循环链表_;

public class CircleLinkedListDemo {

    public static void main(String[] args) {
        CircleLinkedList list = new CircleLinkedList();
        list.add(11);
        list.add(22);
        list.add(33);
        list.add(44);

        list.add(0,55);

        System.out.println(list);
    }

}
