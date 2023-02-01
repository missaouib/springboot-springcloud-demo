package com.example.algorithm._07排序_._01比较排序_._07希尔排序算法_;

import com.example.algorithm._07排序_.Sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 希尔排序把序列看作是一个矩阵，分成 𝑚 列，逐列进行排序
 * 𝑚 从某个整数逐渐减为1
 * 当 𝑚 为1时，整个序列将完全有序
 * @param <T>
 */
@SuppressWarnings("unused")
public class ShellSort<T extends Comparable<T>> extends Sort<T> {

	@Override
	protected void sort() {
		List<Integer> stepSequence = sedgewickStepSequence();
		for (Integer step : stepSequence) {
			sort(step);
		}
	}
	
	/**
	 * 分成step列进行排序
	 */
	private void sort(int step) {
		// col : 第几列，column的简称
		for (int col = 0; col < step; col++) { // 对第col列进行排序
			// col、col+step、col+2*step、col+3*step
			for (int begin = col + step; begin < array.length; begin += step) {
				int cur = begin;
				while (cur > col && cmp(cur, cur - step) < 0) {
					swap(cur, cur - step);
					cur -= step;
				}
			}
		}
	}
	
	private List<Integer> shellStepSequence() {
		List<Integer> stepSequence = new ArrayList<>();
		int step = array.length;
		while ((step >>= 1) > 0) {
			stepSequence.add(step);
		}
		return stepSequence;
	}
	
	private List<Integer> sedgewickStepSequence() {
		List<Integer> stepSequence = new LinkedList<>();
		int k = 0, step = 0;
		while (true) {
			if (k % 2 == 0) {
				int pow = (int) Math.pow(2, k >> 1);
				step = 1 + 9 * (pow * pow - pow);
			} else {
				int pow1 = (int) Math.pow(2, (k - 1) >> 1);
				int pow2 = (int) Math.pow(2, (k + 1) >> 1);
				step = 1 + 8 * pow1 * pow2 - 6 * pow2;
			}
			if (step >= array.length) break;
			stepSequence.add(0, step);
			k++;
		}
		return stepSequence;
	}
}
