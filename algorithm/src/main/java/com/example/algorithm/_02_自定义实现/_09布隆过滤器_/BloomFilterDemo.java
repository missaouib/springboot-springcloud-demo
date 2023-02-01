package com.example.algorithm._02_自定义实现._09布隆过滤器_;

public class BloomFilterDemo {

    public static void main(String[] args) {
		BloomFilter<Integer> bf = new BloomFilter<>(1_00_0000, 0.01);
		for (int i = 1; i <= 1_00_0000; i++) {
			bf.put(i);
		}

		int count = 0;
		for (int i = 1_00_0001; i <= 2_00_0000; i++) {
			if (bf.contains(i)) {
				count++;
			}
		}
		System.out.println(count);
    }

}
