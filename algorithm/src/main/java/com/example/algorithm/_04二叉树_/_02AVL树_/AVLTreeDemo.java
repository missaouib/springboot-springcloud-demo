package com.example.algorithm._04二叉树_._02AVL树_;

import com.example.algorithm.printer.BinaryTrees;

public class AVLTreeDemo {

    public static void main(String[] args) {
        //test1();
        test2();
    }

    /**
     * 新增导致的失衡
     */
    static void test1() {
        Integer data[] = new Integer[] {
                67, 52, 92, 96, 53, 95, 13, 63, 34, 82, 76, 54, 9, 68, 39
        };

        AVLTree<Integer> avl = new AVLTree<>();
        for (int i = 0; i < data.length; i++) {
            avl.add(data[i]);
			System.out.println("【" + data[i] + "】");
			BinaryTrees.println(avl);
			System.out.println("---------------------------------------");
        }

        BinaryTrees.println(avl);
    }

    /**
     * 删除后导致的失衡
     */
    static void test2() {
        Integer data[] = new Integer[] {
                67, 52, 92, 96, 53, 95, 13, 63, 34, 82, 76, 54, 9, 68, 39
        };

        AVLTree<Integer> avl = new AVLTree<>();
        for (int i = 0; i < data.length; i++) {
            avl.add(data[i]);
        }

		for (int i = 0; i < data.length; i++) {
			avl.remove(data[i]);
			System.out.println("【" + data[i] + "】");
			BinaryTrees.println(avl);
			System.out.println("---------------------------------------");
		}


        BinaryTrees.println(avl);
    }

}
