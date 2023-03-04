package com.example.algorithm._08面试题集合_._12零钱兑换;

import java.util.Arrays;

/**
 * 零钱兑换
 * 例子1：假设有 25 分、10 分、5 分、1 分的硬币，现要找给客户 41 分的零钱，如何办到硬币个数最少？
 */
public class CoinChange {

    public static void main(String[] args) {
		coinChange(new Integer[] {25, 10, 5, 1}, 41);

        //coinChange(new Integer[] {25, 20, 5, 1}, 41);
    }


    public static void coinChange(Integer[] faces, int money) {
        Arrays.sort(faces, (Integer f1, Integer f2) -> f2 - f1);
        int coins = 0, i = 0;
        while (i < faces.length) {
            if (money < faces[i]) {
                i++;
                continue;
            }

            System.out.println(faces[i]);
            money -= faces[i];
            coins++;
        }
        System.out.println(coins);
    }
}
