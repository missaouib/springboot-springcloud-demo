package com.example.algorithm._03_反转链表;

import com.example.algorithm._02_自定义实现.List;

public class ListNode {
	public int val;
	public ListNode next;

	public ListNode(int x) {
		val = x;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.val);
		ListNode nextNode = next;
		while (nextNode != null) {
			sb.append("->").append(nextNode.val);
			nextNode = nextNode.next;
		}
		return sb.toString();
	}
}
