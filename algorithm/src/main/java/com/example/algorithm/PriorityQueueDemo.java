package com.example.algorithm;

import java.util.Arrays;
import java.util.PriorityQueue;

public class PriorityQueueDemo {

    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int[] array = {3,45,32,332,90,67,89};
        for (int i = 0;i < array.length;i++) {
            if (queue.size() == 3) {
                if (queue.peek() < array[i]) {
                    queue.remove(queue.peek());
                    queue.add(array[i]);
                }
            } else {
                queue.add(array[i]);
            }

        }
        System.out.println(Arrays.binarySearch(array, 1,4,1));
        System.out.println(queue);
    }
}
