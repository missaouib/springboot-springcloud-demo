package com.example.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SourceTargetDemo {

    public static void main(String[] args) {
        String source ="abcabcbacbacabcddddeee";
        String target = "abc";
        check(source,target);
    }

    public static void check(String source,String target) {
        char[] sourceArray = source.toCharArray();
        char[] targetArray = target.toCharArray();
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0;i < sourceArray.length;i++) {
            int cur = i;
            boolean putFlag = true;
            for (int j = 0;j < targetArray.length;j++) {
                if (sourceArray[cur] == targetArray[j]) {
                    cur++;
                } else {
                    putFlag = false;
                    break;
                }
            }
            if (putFlag) {
                map.put(i,1);
            }
        }
        System.out.println("索引位置："+Arrays.toString(map.keySet().toArray(new Integer[0])));
        System.out.println("出现次数："+map.values().size());
    }
}
