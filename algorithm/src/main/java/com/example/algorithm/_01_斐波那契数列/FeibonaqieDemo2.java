package com.example.algorithm._01_斐波那契数列;

/**
 * 优化算法1
 */
public class FeibonaqieDemo2 {

    public static void main(String[] args) {
        System.out.println(fib2(0));
        System.out.println(fib2(1));
        System.out.println(fib2(2));

        System.out.println(fib2(5));
    }

    /* 0 1 2 3 4 5
     * 0 1 1 2 3 5 8 13 ....
     * i=n-1：一共需要执行多少次
     * first：前一次的second
     * second：first+second的求和
     */
    // O(n)
    public static int fib2(int n) {
        if (n <= 1) return n;

        int first = 0;
        int second = 1;
        for (int i = 0; i < n - 1; i++) {
            int sum = first + second;
            first = second;
            second = sum;
        }
        return second;
    }

}
