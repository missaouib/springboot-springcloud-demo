package com.example.algorithm._02_自定义实现._08最大堆实现优先级队列_;

public class Person implements Comparable<Person> {
	private String name;
	private int boneBreak;
	public Person(String name, int boneBreak) {
		this.name = name;
		this.boneBreak = boneBreak;
	}
	
	@Override
	public int compareTo(Person person) {
		return this.boneBreak - person.boneBreak;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", boneBreak=" + boneBreak + "]";
	}
}
