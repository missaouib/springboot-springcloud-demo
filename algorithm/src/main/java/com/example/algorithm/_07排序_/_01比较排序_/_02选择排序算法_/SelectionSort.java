package com.example.algorithm._07排序_._01比较排序_._02选择排序算法_;


import com.example.algorithm._07排序_.Sort;

/**
 * ① 从序列中找出最大的那个元素，然后与最末尾的元素交换位置
 * ✓ 执行完一轮后，最末尾的那个元素就是最大的元素
 * ② 忽略 ① 中曾经找到的最大元素，重复执行步骤 ①
 *
 * 选择排序的交换次数要远远少于冒泡排序，平均性能优于冒泡排序
 * @param <T>
 */
public class SelectionSort<T extends Comparable<T>> extends Sort<T> {

	@Override
	protected void sort() {
		
//		for (int end = array.length - 1; end > 0; end--) {
//			int max = 0;
//			for (int begin = 1; begin <= end; begin++) {
//				if (cmp(max, begin) <= 0) {
//					max = begin;
//				}
//			}
//			swap(max, end);
//		}
		
		for (int end = array.length - 1; end > 0; end--) {
			int max = 0;
			for (int begin = 1; begin <= end; begin++) {
				if (cmp(max, begin) < 0) {
					max = begin;
				}
			}
			swap(max, end);
		}
		
		// 7 5 10 1 4 2 10 
	}

}
