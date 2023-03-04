package com.example.algorithm._08面试题集合_._05删除链表中的节点;

import com.example.algorithm._03_反转链表.ListNode;

/**
 * 删除链表中的节点
 * https://leetcode.cn/problems/delete-node-in-a-linked-list/
 */
public class DeleteNode {

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
