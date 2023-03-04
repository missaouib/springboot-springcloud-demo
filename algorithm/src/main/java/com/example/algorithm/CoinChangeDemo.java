package com.example.algorithm;

import java.util.Arrays;

public class CoinChangeDemo {

    public static void main(String[] args) {
        int money = 65;
        int[] arrays = new int[]{10,5,25,1};
        System.out.println(coinChange(41,arrays));
    }

    public static int coinChange(int money,int[] arrays) {
        int[] dp = new int[money+1];
        for (int i = 1;i <= money;i++) {
            int min = dp[i-1];
            if (i >= 1) {
                min = Math.min(dp[i-1],min);
            }
            if (i >= 5) {
                min = Math.min(dp[i-5],min);
            }
            if (i >= 10) {
                min = Math.min(dp[i-10],min);
            }
            if (i >= 25) {
                min = Math.min(dp[i-25],min);
            }
            dp[i] = min + 1;
        }

        return dp[money];
    }
}
