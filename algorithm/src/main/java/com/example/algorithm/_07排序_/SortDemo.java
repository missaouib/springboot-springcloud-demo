package com.example.algorithm._07排序_;

import com.example.algorithm._07排序_._01比较排序_._01冒泡算法_.BubbleSort3;
import com.example.algorithm._07排序_._01比较排序_._02选择排序算法_.SelectionSort;
import com.example.algorithm._07排序_._01比较排序_._03堆排序算法_.HeapSort;
import com.example.algorithm._07排序_._01比较排序_._04插入排序算法_.InsertionSort1;
import com.example.algorithm._07排序_._01比较排序_._04插入排序算法_.InsertionSort2;
import com.example.algorithm._07排序_._01比较排序_._04插入排序算法_.InsertionSort3;
import com.example.algorithm._07排序_._01比较排序_._05归并排序算法_.MergeSort;
import com.example.algorithm._07排序_._01比较排序_._06快速排序算法_.QuickSort;
import com.example.algorithm._07排序_._01比较排序_._07希尔排序算法_.ShellSort;
import com.example.algorithm._07排序_.tools.Asserts;
import com.example.algorithm._07排序_.tools.Integers;

import java.util.Arrays;

public class SortDemo {

    public static void main(String[] args) {
        Integer[] array = Integers.random(20000,1,20000);

        testSorts(array,
//				new RadixSort()
				new InsertionSort1(),
				new InsertionSort2(),
                new InsertionSort3(),
                new SelectionSort(),
                new HeapSort(),
                new MergeSort(),
                new BubbleSort3(),
                new QuickSort(),
                new ShellSort()
        );
    }

    public static void testSorts(Integer[] array, Sort... sorts) {
        for (Sort sort : sorts) {
            Integer[] newArray = Integers.copy(array);
            sort.sort(newArray);
            Asserts.test(Integers.isAscOrder(newArray));
        }
        Arrays.sort(sorts);

        for (Sort sort : sorts) {
            System.out.println(sort);
        }
    }
}
