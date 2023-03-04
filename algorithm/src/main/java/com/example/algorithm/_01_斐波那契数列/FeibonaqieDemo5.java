package com.example.algorithm._01_斐波那契数列;

/**
 * 完全去除递归调用
 */
public class FeibonaqieDemo5 {

    public static void main(String[] args) {
        System.out.println(fib5(1));
        System.out.println(fib5(2));

        System.out.println(fib5(7));

    }


    public static int fib5(int n) {
        if (n <= 2) return 1;
        int[] array = new int[n + 1];
        array[1] = array[2] = 1;
        for (int i = 3; i <= n; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        return array[n];
    }

}
