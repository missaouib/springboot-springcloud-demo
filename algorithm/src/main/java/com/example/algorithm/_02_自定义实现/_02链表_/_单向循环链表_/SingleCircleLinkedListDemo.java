package com.example.algorithm._02_自定义实现._02链表_._单向循环链表_;

public class SingleCircleLinkedListDemo {

    public static void main(String[] args) {
        SingleCircleLinkedList list = new SingleCircleLinkedList();
        list.add(11);
        list.add(22);
        list.add(33);
        list.add(44);

        list.add(0,55);

        System.out.println(list);
    }

}
