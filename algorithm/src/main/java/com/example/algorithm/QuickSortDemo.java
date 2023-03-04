package com.example.algorithm;

import java.util.Arrays;
import java.util.Random;

public class QuickSortDemo {

    public static void main(String[] args) {
        int[] arrays = {3,32,89,43,75};
        quickSort(arrays,0,arrays.length);
        System.out.println(Arrays.toString(arrays));
    }

    public static void quickSort(int[] arrays,int begin,int end) {
        if (end - begin < 2) return;

        int pivotIndex = pivotIndex(arrays,begin,end);
        quickSort(arrays,begin,pivotIndex);
        quickSort(arrays,pivotIndex+1,end);
    }

    private static int pivotIndex(int[] arrays,int begin, int end) {
        //随机选择一个元素作为轴点元素
        int randomIndex = (int)(Math.random()*(end-begin))+begin;
        arrays[randomIndex] = arrays[randomIndex]^arrays[begin];
        arrays[begin] = arrays[randomIndex]^arrays[begin];
        arrays[randomIndex] = arrays[randomIndex]^arrays[begin];

        int pivot = arrays[begin];
        end--;

        while (begin < end) {
            while (begin < end) {
                if (arrays[end] >= pivot) {
                    end--;
                } else {
                    arrays[begin++] = arrays[end];
                    break;
                }
            }

            while (begin < end) {
                if (arrays[begin] <= pivot) {
                    begin++;
                } else {
                    arrays[end--] = arrays[begin];
                    break;
                }
            }
        }

        arrays[begin] = pivot;
        return begin;
    }
}
