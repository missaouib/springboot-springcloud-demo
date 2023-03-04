package com.example.algorithm;

import com.example.algorithm._03_反转链表.ListNode;

public class RevertListNodeDemo {

    public static void main(String[] args) {
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node1 = new ListNode(1);

        node5.next = node4;
        node4.next = node3;
        node3.next = node2;
        node2.next = node1;

        System.out.println(node5);
        System.out.println("==============================");
        ListNode newHead = revertList2(node5);
        System.out.println(newHead);
    }

    public static ListNode revertList1(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode newHead = revertList1(head.next);
        head.next.next = head;
        head.next = null;

        return newHead;
    }

    public static ListNode revertList2(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode newHead = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }

        return newHead;
    }
}
