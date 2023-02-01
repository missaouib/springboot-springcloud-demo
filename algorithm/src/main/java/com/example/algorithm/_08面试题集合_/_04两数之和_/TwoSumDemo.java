package com.example.algorithm._08面试题集合_._04两数之和_;

import com.example.algorithm._02_自定义实现._06红黑树实现HashMap_.HashMap;
import com.example.algorithm._02_自定义实现._06红黑树实现HashMap_.Map;

import java.util.Arrays;

/**
 * 两数之和
 */
public class TwoSumDemo {

    public static void main(String[] args) {
        int[] nums = new int[]{2,7,11,15};
        int target = 9;

        System.out.println(Arrays.toString(twoSum1(nums,target)));
        System.out.println(Arrays.toString(twoSum2(nums,target)));
    }

    /**
     * 解法1：暴力枚举
     * @return
     */
    public static int[] twoSum1(int[] nums,int target) {
        for (int i = 0;i < nums.length;i++) {
            for (int j = i+1; j < nums.length;j++) {
                if (target - nums[i] == nums[j]) {
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    /**
     * 解法2：Hash，更优解法
     * @return
     */
    public static int[] twoSum2(int[] nums,int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0;i < nums.length;i++) {
            int partnerNumber = target - nums[i];
            if (map.containsKey(partnerNumber)) {
                return new int[]{map.get(partnerNumber),i};
            }
            map.put(nums[i],i);
        }

        return null;
    }
}
