package com.example.algorithm._08面试题集合_._02汉诺塔_;

/**
 * 其实分 2 种情况讨论即可
 * 当 n == 1时，直接将盘子从 A 移动到C
 * 当 n > 1时，可以拆分成3大步骤
 * ①将 n– 1 个盘子从 A 移动到B
 * ② 将编号为 n 的盘子从 A 移动到C
 * ③将 n– 1 个盘子从 B 移动到C
 * ✓ 步骤①③ 明显是个递归调用
 */
public class Hanoi {
	
	public static void main(String[] args) {
		new Hanoi().hanoi(10, "A", "B", "C");
	}
	
	/**
	 * 将 n 个碟子从 p1 挪动到 p3
	 * @param p2 中间的柱子
	 */
	void hanoi(int n, String p1, String p2, String p3) {
		if (n == 1) {
			move(n, p1, p3);
			return;
		}
		hanoi(n - 1, p1, p3, p2);
		move(n, p1, p3);
		hanoi(n - 1, p2, p1, p3);
	} 
	// T(n) = 2 * [2 * T(n - 2) + O(1)] + O(1)
	// T(n) = 2^2 * [2 * T(n - 3) + O(1)] + 2 * O(1) + O(1)
	// T(n) = 2^3 * T(n - 3) + (2^2 + 2^1 + 2^0) * O(1)
	// T(n) = 2^(n - 1) * O(1) + (2^(n-2) + ... + 2^2 + 2^1 + 2^0) * O(1)
	// T(n) = [2^(n - 1)+ 2^(n-2) + ... + 2^2 + 2^1 + 2^0] * O(1)
	// T(n) = (2^n - 1) * O(1)
	
	/**
	 * 将 no 号盘子从 from 移动到 to
	 * @param no
	 * @param from
	 * @param to
	 */
	void move(int no, String from, String to) {
		System.out.println("将" + no + "号盘子从" + from + "移动到" + to);
	}
	
}	
