package com.example.algorithm._04二叉树_._01二叉搜索树_._01前中后序递归遍历_;

import com.example.algorithm.printer.BinaryTrees;

public class BSTDemo {

    public static void main(String[] args) {
        test1();
        System.out.println("====================");
        System.out.println("====================");
        test2();
    }

    /**
     * 构建二叉搜索树
     */
    static void test1() {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };

        BST<Integer> bst = new BST<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        //打印二叉树
        BinaryTrees.println(bst);
    }


    /**
     * 前序遍历
     * 中序遍历
     * 后序遍历
     * 层序遍历
     */
    static void test2() {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 1
        };

        BST<Integer> bst = new BST<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        BinaryTrees.println(bst);

        bst.preorder(new BinaryTree.Visitor<Integer>() {
            public boolean visit(Integer element) {
                System.out.print(element + " ");
                return element == 2 ? true : false;
            }
        });
        System.out.println();

        bst.inorder(new BinaryTree.Visitor<Integer>() {
            public boolean visit(Integer element) {
                System.out.print(element + " ");
                return element == 4 ? true : false;
            }
        });
        System.out.println();

        bst.postorder(new BinaryTree.Visitor<Integer>() {
            public boolean visit(Integer element) {
                System.out.print(element + " ");
                return element == 4 ? true : false;
            }
        });
        System.out.println();

        bst.levelOrder(new BinaryTree.Visitor<Integer>() {
            public boolean visit(Integer element) {
                System.out.print(element + " ");
                return element == 9 ? true : false;
            }
        });
        System.out.println();
    }

    /**
     * 删除
     */
    static void test3() {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };

        BST<Integer> bst = new BST<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        BinaryTrees.println(bst);

        bst.remove(7);

        BinaryTrees.println(bst);
    }
}
