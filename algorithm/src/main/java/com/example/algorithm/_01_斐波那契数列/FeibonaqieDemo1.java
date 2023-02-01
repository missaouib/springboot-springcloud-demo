package com.example.algorithm._01_斐波那契数列;

public class FeibonaqieDemo1 {

    public static void main(String[] args) {
        System.out.println(fib1(0));
        System.out.println(fib1(1));
        System.out.println(fib1(2));

        System.out.println(fib1(5));

        //很耗时
        //fib1(64);
    }


    /*
     * 0 1 1 2 3 5 8 13 ....
     */
    // O(2^n)
    //fib(5)=1+2+4+8=2^0+2^1+2^2+2^3=2^4-1=2^(n-1)-1=0.5*2^n
    public static int fib1(int n) {
        if (n <= 1) return n;
        return fib1(n - 1) + fib1(n - 2);
    }

}
