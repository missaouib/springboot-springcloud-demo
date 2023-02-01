package com.example.algorithm._02_自定义实现._04队列_._01队列_;

import com.example.algorithm._02_自定义实现.List;
import com.example.algorithm._02_自定义实现._02链表_._双向链表_.LinkedList;

public class Queue<E> {
	private List<E> list = new LinkedList<>();
	
	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public void clear() {
		list.clear();
	}

	public void enQueue(E element) {
		list.add(element);
	}

	public E deQueue() {
		return list.remove(0);
	}

	public E front() {
		return list.get(0);
	}
}
