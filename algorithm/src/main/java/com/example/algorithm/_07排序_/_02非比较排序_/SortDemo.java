package com.example.algorithm._07排序_._02非比较排序_;

import com.example.algorithm._07排序_.Sort;
import com.example.algorithm._07排序_._02非比较排序_._01计数排序算法_.CountingSort;
import com.example.algorithm._07排序_._02非比较排序_._02基数排序算法_.RadixSort;
import com.example.algorithm._07排序_.tools.Asserts;
import com.example.algorithm._07排序_.tools.Integers;

import java.util.Arrays;

public class SortDemo {

    public static void main(String[] args) {
        Integer[] array = {7, 3, 5, 8, 6, 7, 4, 5};

        testSorts(array,
				new CountingSort(),
                new RadixSort()
        );
    }

    public static void testSorts(Integer[] array, Sort... sorts) {
        for (Sort sort : sorts) {
            Integer[] newArray = Integers.copy(array);
            sort.sort(newArray);
            Integers.println(newArray);
            Asserts.test(Integers.isAscOrder(newArray));
        }
        Arrays.sort(sorts);

        for (Sort sort : sorts) {
            System.out.println(sort);
        }
    }

}
