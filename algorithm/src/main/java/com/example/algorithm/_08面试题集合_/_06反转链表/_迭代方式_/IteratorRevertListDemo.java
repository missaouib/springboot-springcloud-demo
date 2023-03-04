package com.example.algorithm._08面试题集合_._06反转链表._迭代方式_;

import com.example.algorithm._03_反转链表.ListNode;

/**
 * 输入：1->2->3->4->5->null
 * 结果：5->4->3->2->1->null
 */
public class IteratorRevertListDemo {

    public static void main(String[] args) {

    }

    public static ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode newHead = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }

        return newHead;
    }
}
