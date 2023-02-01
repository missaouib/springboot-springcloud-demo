package com.example.algorithm._02_自定义实现._08最大堆实现优先级队列_.heap;

import java.util.Comparator;

@SuppressWarnings("unchecked")
public abstract class AbstractHeap<E> implements Heap<E> {
	protected int size;
	protected Comparator<E> comparator;
	
	public AbstractHeap(Comparator<E> comparator) {
		this.comparator = comparator;
	}
	
	public AbstractHeap() {
		this(null);
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	protected int compare(E e1, E e2) {
		return comparator != null ? comparator.compare(e1, e2) 
				: ((Comparable<E>)e1).compareTo(e2);
	}
}
