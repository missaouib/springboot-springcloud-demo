package com.example.algorithm._08面试题集合_._06反转链表._递归方式_;

import com.example.algorithm._03_反转链表.ListNode;

/**
 * 输入：1->2->3->4->5->null
 * 结果：5->4->3->2->1->null
 */
public class RecursiveRevertListDemo {

    public static void main(String[] args) {

    }

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

}
