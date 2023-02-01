package com.example.algorithm._08面试题集合_._01爬楼梯_;

/**
 * ◼ 楼梯有 n 阶台阶，上楼可以一步上 1 阶，也可以一步上 2 阶，走完 n 阶台阶共有多少种不同的走法？
 * 假设 n 阶台阶有 f(n) 种走法，第 1 步有 2 种走法
 * ✓ 如果上 1 阶，那就还剩 n – 1 阶，共 f(n – 1) 种走法
 * ✓ 如果上 2 阶，那就还剩 n – 2 阶，共 f(n – 2) 种走法
 * 所以 f(n) = f(n – 1) + f(n – 2)
 */
public class ClimbStairs {

	public static void main(String[] args) {
		System.out.println(climbStairs(3));

		System.out.println(climbStairs(5));
	}

	public static int climbStairs(int n) {
		if (n <= 2) return n;
		return climbStairs(n - 1) + climbStairs(n - 2);
	}
	
}
