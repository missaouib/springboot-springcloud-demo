package com.example.algorithm._05二叉堆_;

import com.example.algorithm.printer.BinaryTrees;

import java.util.Comparator;

public class BinaryHeapDemo {

    public static void main(String[] args) {
        //test1();
        test2();
    }

    /**
     * 最大堆
     */
    public static void test1() {
        Integer[] data = {88, 44, 53, 41, 16, 6, 70, 18, 85, 98, 81, 23, 36, 43, 37};
        BinaryHeap<Integer> heap = new BinaryHeap<>(data, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                //最大堆
                return o1 - o2;
            }
        });
        BinaryTrees.println(heap);
    }

    /**
     * 最小堆
     */
    public static void test2() {
        Integer[] data = {88, 44, 53, 41, 16, 6, 70, 18, 85, 98, 81, 23, 36, 43, 37};
        BinaryHeap<Integer> heap = new BinaryHeap<>(data, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                //最小堆
                return o2 - o1;
            }
        });
        BinaryTrees.println(heap);
    }
}
