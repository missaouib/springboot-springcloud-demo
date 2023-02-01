package com.example.algorithm._07排序_._01比较排序_._04插入排序算法_;


import com.example.algorithm._07排序_.Sort;

/**
 * 优化1：
 * 思路是将【交换】转为【挪动】
 * ① 先将待插入的元素备份
 * ② 头部有序数据中比待插入元素大的，都朝尾部方向挪动1个位置
 * ③ 将待插入元素放到最终的合适位置
 * @param <T>
 */
public class InsertionSort2<T extends Comparable<T>> extends Sort<T> {

	@Override
	protected void sort() {
		for (int begin = 1; begin < array.length; begin++) {
			int cur = begin;
			T v = array[cur];
			while (cur > 0 && cmp(v, array[cur - 1]) < 0) {
				array[cur] = array[cur - 1];
				cur--;
			}
			array[cur] = v;
		}
	}

}
