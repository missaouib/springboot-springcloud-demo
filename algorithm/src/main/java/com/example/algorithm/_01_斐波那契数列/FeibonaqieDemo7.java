package com.example.algorithm._01_斐波那契数列;

/**
 * 使用线性代数计算公式计算
 */
public class FeibonaqieDemo7 {

    public static void main(String[] args) {
        System.out.println(fib8(1));
        System.out.println(fib8(2));
        System.out.println(fib8(7));

    }

    public static int fib8(int n) {
        double c = Math.sqrt(5);
        return (int)((Math.pow((1+c)/2,n)-Math.pow((1-c)/2,n))/c);
    }

    /*
     * 滚动数组优化版，& 1 改造 只适用于与2取模
     * 4 % 2 = 0  0b100 & 0b001 = 0
     * 3 % 2 = 1  0b011 & 0b001 = 1
     * 5 % 2 = 1  0b101 & 0b001 = 1
     * 6 % 2 = 0  0b110 & 0b001 = 0
     */

    public static int fib7(int n) {
        if (n <= 2) return 1;
        int[] array = new int[2];
        array[0] = array[1] = 1;
        for (int i = 3; i <= n; i++) {
            array[i & 1] = array[(i - 1) & 1] + array[(i - 2) & 1];
        }
        return array[n & 1];
    }

}
