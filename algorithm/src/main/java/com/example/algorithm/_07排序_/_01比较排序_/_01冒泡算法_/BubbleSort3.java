package com.example.algorithm._07排序_._01比较排序_._01冒泡算法_;


import com.example.algorithm._07排序_.Sort;

/**
 * 最优算法
 * 优化2：如果序列尾部已经局部有序，可以记录最后1次交换的位置，减少比较次数
 * @param <T>
 */
public class BubbleSort3<T extends Comparable<T>> extends Sort<T> {

	@Override
	protected void sort() {
		for (int end = array.length - 1; end > 0; end--) {
			int sortedIndex = 1;
			for (int begin = 1; begin <= end; begin++) {
				// if (array[begin] < array[begin - 1]) {
				if (cmp(begin, begin - 1) < 0) {
					swap(begin, begin - 1);
					sortedIndex = begin;
				}
			}
			end = sortedIndex;
		}
	}

}
