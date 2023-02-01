package com.example.algorithm._07排序_._01比较排序_._03堆排序算法_;


import com.example.algorithm._07排序_.Sort;

/**
 * 堆排序可以认为是对选择排序的一种优化
 *
 * 执行流程
 * ① 对序列进行原地建堆（heapify）
 * ② 重复执行以下操作，直到堆的元素数量为 1
 * ✓ 交换堆顶元素与尾元素
 * ✓ 堆的元素数量减 1
 * ✓ 对 0 位置进行 1 次 siftDown 操作
 *
 * 最好、最坏、平均时间复杂度：O(nlogn)，空间复杂度：O(1)，属于不稳定排序
 * @param <T>
 */
public class HeapSort<T extends Comparable<T>> extends Sort<T> {
	private int heapSize;

	@Override
	protected void sort() {
		// 原地建堆
		heapSize = array.length;
		for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
			siftDown(i);
		}
		
		while (heapSize > 1) {
			// 交换堆顶元素和尾部元素
			swap(0, --heapSize);

			// 对0位置进行siftDown（恢复堆的性质）
			siftDown(0);
		}
	}
	
	private void siftDown(int index) {
		T element = array[index];
		
		int half = heapSize >> 1;
		while (index < half) { // index必须是非叶子节点
			// 默认是左边跟父节点比
			int childIndex = (index << 1) + 1;
			T child = array[childIndex];
			
			int rightIndex = childIndex + 1;
			// 右子节点比左子节点大
			if (rightIndex < heapSize && 
					cmp(array[rightIndex], child) > 0) { 
				child = array[childIndex = rightIndex];
			}
			
			// 大于等于子节点
			if (cmp(element, child) >= 0) break;
			
			array[index] = child;
			index = childIndex;
		}
		array[index] = element;
	}
}
