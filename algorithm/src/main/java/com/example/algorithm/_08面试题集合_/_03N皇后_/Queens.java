package com.example.algorithm._08面试题集合_._03N皇后_;

/**
 * 在8x8格的国际象棋上摆放八个皇后，使其不能互相攻击：任意两个皇后都不能处于同一行、同一列、同一斜线上
 * 请问有多少种摆法？
 */
public class Queens {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Queens().placeQueens(4);
	}
	
	/**
	 * 数组索引是行号，数组元素是列号
	 */
	int[] cols;
	/**
	 * 一共有多少种摆法
	 */
	int ways;
	
	void placeQueens(int n) {
		if (n < 1) return;
		cols = new int[n];
		place(0);
		System.out.println(n + "皇后一共有" + ways + "种摆法");
	}
	
	/**
	 * 从第row行开始摆放皇后
	 * @param row
	 */
	void place(int row) {
		if (row == cols.length) {
			ways++;
			show();
			return;
		}
		
		for (int col = 0; col < cols.length; col++) {
			if (isValid(row, col)) {
				// 在第row行第col列摆放皇后
				cols[row] = col;
				place(row + 1);
			}
		}
	}
	
	/**
	 * 判断第row行第col列是否可以摆放皇后
	 */
	boolean isValid(int row, int col) {
		for (int i = 0; i < row; i++) {
			// 第col列已经有皇后
			if (cols[i] == col) {
				//System.out.println("[" + row + "][" + col + "]=false");
				return false;
			}
			// 第i行的皇后跟第row行第col列格子处在同一斜线上
			if (row - i == Math.abs(col - cols[i])) {
				//System.out.println("[" + row + "][" + col + "]=false");
				return false;
			}
		}
		//System.out.println("[" + row + "][" + col + "]=true");
		return true;
	}
	
	void show() {
		for (int row = 0; row < cols.length; row++) {
			for (int col = 0; col < cols.length; col++) {
				if (cols[row] == col) {
					System.out.print("1 ");
				} else {
					System.out.print("0 ");
				}
			}
			System.out.println();
		}
		System.out.println("------------------------------");
	}
}
