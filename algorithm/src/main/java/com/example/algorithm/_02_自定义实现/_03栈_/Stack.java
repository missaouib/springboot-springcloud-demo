package com.example.algorithm._02_自定义实现._03栈_;

import com.example.algorithm._02_自定义实现.List;
import com.example.algorithm._02_自定义实现._01数组_.ArrayList;
//也可通过继承的方式，不过不推荐
public class Stack<E> {

	//也可使用LinkedList
	private List<E> list = new ArrayList<>();
	
	public void clear() {
		list.clear();
	}
	
	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public void push(E element) {
		list.add(element);
	}


	public E pop() {
		return list.remove(list.size() - 1);
	}


	public E top() {
		return list.get(list.size() - 1);
	}
}