package com.example.algorithm._02_自定义实现._04队列_._02双端队列_;


public class DequeDemo {

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.enQueueFront(11);
        deque.enQueueFront(22);
        deque.enQueueRear(33);
        deque.enQueueRear(44);

        while (!deque.isEmpty()) {
            System.out.println(deque.deQueueFront());
        }
    }

}
