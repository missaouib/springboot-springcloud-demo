package com.example.algorithm._08面试题集合_._11最优装载问题_加勒比海盗;

import java.util.Arrays;

/**
 * 最优装载问题（加勒比海盗）
 */
public class Pirate {

    public static void main(String[] args) {
        int[] weights = {3, 5, 4, 10, 7, 14, 2, 11};
        Arrays.sort(weights);
        int capacity = 30, weight = 0, count = 0;

        for (int i = 0; i < weights.length && weight < capacity; i++) {
            int newWeight = weight + weights[i];
            if (newWeight <= capacity) {
                weight = newWeight;
                count++;
                System.out.println(weights[i]);
            }
        }

        System.out.println("一共选了" + count + "件古董");
    }
}
