package com.example.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AlgorithmDemo {

    public static void main(String[] args) {
        int[] arrays = new int[]{2,56,34,27,5,67};
//        System.out.println(Arrays.toString(bubbleSort(arrays)));
//        System.out.println("=====================================");
//        System.out.println(Arrays.toString(quickSort(arrays)));
//        System.out.println("=====================================");
//        System.out.println(Arrays.toString(insertSort(arrays)));
//        System.out.println("=====================================");
        System.out.println(Arrays.toString(twoSum1(arrays,29)));
        System.out.println("=====================================");
        System.out.println(Arrays.toString(twoSum2(arrays,29)));
    }

    public static int[] bubbleSort(int[] arrays) {
        for (int end = arrays.length - 1;end > 0;end--) {
            for (int begin = 1;begin <= end ;begin++) {
                if (arrays[begin] < arrays[begin-1]) {
                    int temp = arrays[begin];
                    arrays[begin] = arrays[begin-1];
                    arrays[begin-1] = temp;
                }
            }
        }
        return arrays;
    }

    public static int[] quickSort(int[] arrays) {
        for (int end = arrays.length - 1;end > 0;end--) {
            int max = 0;
            for (int begin = 1;begin <= end ;begin++) {
                if (arrays[begin] > arrays[max]) {
                    max = begin;
                }
            }

            int temp = arrays[max];
            arrays[max] = arrays[end];
            arrays[end] = temp;

        }
        return arrays;
    }

    public static int[] insertSort(int[] arrays) {
        for (int begin = 1;begin < arrays.length;begin++) {
            int cur = begin;
            while (cur > 0 && arrays[begin] < arrays[begin-1]) {
                int temp = arrays[begin];
                arrays[begin] = arrays[begin-1];
                arrays[begin-1] = temp;
                cur--;
            }
        }
        return arrays;
    }

    public static int[] twoSum1(int[] arrays,int target) {
        for (int i = 0; i < arrays.length;i++) {
            for (int j = i+1; j < arrays.length;j++) {
                if (target - arrays[i] == arrays[j]) {
                    return new int[] {i,j};
                }
            }
        }
        return null;
    }

    public static int[] twoSum2(int[] arrays,int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0;i < arrays.length;i++) {
            int partnerNumber = target - arrays[i];
            if (map.containsKey(partnerNumber)) {
                return new int[]{map.get(partnerNumber),i};
            }
            map.put(arrays[i],i);
        }
        return null;
    }

    public static void hanoi(int n,String p1,String p2,String p3) {
        if (n == 1) {
            move(n,p1,p3);
            return;
        }
        hanoi(n-1,p1,p3,p2);
        move(n,p1,p3);
        hanoi(n-1,p2,p1,p3);
    }

    public static void move(int no,String from ,String to) {

    }
}
