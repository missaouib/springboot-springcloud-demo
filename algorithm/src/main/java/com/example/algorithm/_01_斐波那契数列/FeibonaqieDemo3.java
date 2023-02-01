package com.example.algorithm._01_斐波那契数列;

/**
 * 最优算法
 */
public class FeibonaqieDemo3 {

    public static void main(String[] args) {
        System.out.println(fib3(0));
        System.out.println(fib3(1));
        System.out.println(fib3(2));

        System.out.println(fib3(5));
    }


    public static int fib3(int n) {
        if (n <= 1) return n;

        int first = 0;
        int second = 1;
        while (n-- > 1) {
            second += first;
            first = second - first;
        }
        return second;
    }

}
