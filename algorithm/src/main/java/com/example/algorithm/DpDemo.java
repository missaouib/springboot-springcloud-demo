package com.example.algorithm;

public class DpDemo {

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[] {-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println(lcs(new int[] {10, 2, 2, 5, 1, 7, 101, 18}));
        System.out.println(lcSubsequence(new int[] {1, 3, 5, 9, 10}, new int[] {1, 4, 9, 10}));
        System.out.println(lcSubString("ABDCBA", "ABBA"));
    }

    public static int maxSubArray(int[] arrays) {
        int[] dp = new int[arrays.length];
        dp[0] = arrays[0];
        int max = dp[0];
        for (int i = 1;i < arrays.length;i++) {
            int prev = dp[i-1];
            if (prev <= 0) {
                dp[i] = arrays[i];
            } else {
                dp[i] = prev + arrays[i];
            }
            max = Math.max(dp[i],max);
        }
        return max;
    }

    public static int lcs(int[] arrays) {
        int[] dp = new int[arrays.length];
        int max = dp[0] = 1;
        for (int i = 1; i < arrays.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arrays[i] <= arrays[j]) {
                    continue;
                } else {
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            max = Math.max(dp[i],max);
        }
        return max;
    }

    public static int lcSubsequence(int[] arrays1,int[] arrays2){
        int[][] dp = new int[arrays1.length+1][arrays2.length+1];
        for (int i = 1; i <= arrays1.length; i++) {
            for (int j = 1; j <= arrays2.length; j++) {
                if (arrays1[i-1] == arrays2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[arrays1.length][arrays2.length];
    }

    public static int lcSubString(String s1,String s2) {
        char[] array1 = s1.toCharArray();
        char[] array2 = s2.toCharArray();
        int[][] dp = new int[array1.length+1][array2.length+1];
        int max = 0;
        for (int i = 1; i <= array1.length; i++) {
            for (int j = 1; j <= array2.length; j++) {
                if (array1[i-1] != array2[j-1]) {
                    continue;
                } else {
                    dp[i][j] = dp[i-1][j-1]+1;
                    max = Math.max(dp[i][j], max);
                }
            }
        }
        return max;
    }
}
