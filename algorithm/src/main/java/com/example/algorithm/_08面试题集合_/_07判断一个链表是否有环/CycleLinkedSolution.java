package com.example.algorithm._08面试题集合_._07判断一个链表是否有环;

import com.example.algorithm._02_自定义实现.List;
import com.example.algorithm._03_反转链表.ListNode;

/**
 * 判断一个链表是否有环
 * https://leetcode.cn/problems/linked-list-cycle/
 */
public class CycleLinkedSolution {

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) return true;
        }
        return false;
    }
}
