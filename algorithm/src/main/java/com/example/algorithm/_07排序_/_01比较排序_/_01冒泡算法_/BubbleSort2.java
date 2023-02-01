package com.example.algorithm._07排序_._01比较排序_._01冒泡算法_;


import com.example.algorithm._07排序_.Sort;

/**
 * 优化1：如果序列已经完全有序，可以提前终止冒泡排序
 * @param <T>
 */
public class BubbleSort2<T extends Comparable<T>> extends Sort<T> {

	@Override
	protected void sort() {
		for (int end = array.length - 1; end > 0; end--) {
			boolean sorted = true;
			for (int begin = 1; begin <= end; begin++) {
				// if (array[begin] < array[begin - 1]) {
				if (cmp(begin, begin - 1) < 0) {
					swap(begin, begin - 1);
					sorted = false;
				}
			}
			if (sorted) break;
		}
	}

}
