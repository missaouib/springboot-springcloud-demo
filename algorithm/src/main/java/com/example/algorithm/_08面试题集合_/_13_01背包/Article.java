package com.example.algorithm._08面试题集合_._13_01背包;

public class Article {
	public int weight;
	public int value;
	public double valueDensity;
	public Article(int weight, int value) {
		this.weight = weight;
		this.value = value;
		valueDensity = value * 1.0 / weight;
	}
	@Override
	public String toString() {
		return "Article [weight=" + weight + ", value=" + value + ", valueDensity=" + valueDensity + "]";
	}
}
