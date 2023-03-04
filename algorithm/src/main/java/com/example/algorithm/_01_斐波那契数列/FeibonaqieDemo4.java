package com.example.algorithm._01_斐波那契数列;

/**
 * 减少递归调用，避免重复计算
 */
public class FeibonaqieDemo4 {

    public static void main(String[] args) {
        System.out.println(fib4(1));
        System.out.println(fib4(2));

        System.out.println(fib4(7));

    }


    public static int fib4(int n) {
        if (n <= 2) return 1;
        //数量为n，保证最大索引为n
        int[] array = new int[n + 1];
        array[1] = array[2] = 1;
        return fib4(n, array);
    }

    public static int fib4(int n, int[] array) {
        if (array[n] == 0) {
            array[n] = fib4(n - 1, array) + fib4(n - 2, array);
        }
        return array[n];
    }

}
