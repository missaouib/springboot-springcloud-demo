package com.example.algorithm._07排序_._01比较排序_._04插入排序算法_;


import com.example.algorithm._07排序_.Sort;

/**
 * 优化2：
 * 二分搜索优化，在元素 v 的插入过程中，可以先二分搜索出合适的插入位置，然后再将元素 v 插入
 * 要求二分搜索返回的插入位置：第1个大于 v 的元素位置
 * @param <T>
 */
public class InsertionSort3<T extends Comparable<T>> extends Sort<T> {

	
//	protected void sort() {
//		for (int begin = 1; begin < array.length; begin++) {
//			T v = array[begin];
//			int insertIndex = search(begin);
//			// 将 [insertIndex, begin) 范围内的元素往右边挪动一个单位
////			for (int i = begin - 1; i >= insertIndex; i--) {
////				
////			}
//			for (int i = begin; i > insertIndex; i--) {
//				array[i] = array[i - 1];
//			}
//			array[insertIndex] = v;
//		}
//	}
	
	@Override
	protected void sort() {
		for (int begin = 1; begin < array.length; begin++) {
			insert(begin, search(begin));
		}
	}
	
	/**
	 * 将source位置的元素插入到dest位置
	 * @param source
	 * @param dest
	 */
	private void insert(int source, int dest) {
		T v = array[source];
		for (int i = source; i > dest; i--) {
			array[i] = array[i - 1];
		}
		array[dest] = v;
	}
	
	/**
	 * 利用二分搜索找到 index 位置元素的待插入位置
	 * 已经排好序数组的区间范围是 [0, index)
	 * @param index
	 * @return
	 */
	private int search(int index) {
		int begin = 0;
		int end = index;
		while (begin < end) {
			int mid = (begin + end) >> 1;
			if (cmp(array[index], array[mid]) < 0) {
				end = mid;
			} else {
				begin = mid + 1;
			}
		}
		return begin;
	}
}
