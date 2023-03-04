package com.example.algorithm._08面试题集合_._09用栈实现队列;

import java.util.Stack;

/**
 * 用栈实现队列
 * https://leetcode.cn/problems/implement-queue-using-stacks/
 */
public class ImplementQueueUsingStacks {

    private Stack<Integer> inStack;
    private Stack<Integer> outStack;

    /** Initialize your data structure here. */
    public ImplementQueueUsingStacks() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    /** 入队 */
    public void push(int x) {
        inStack.push(x);
    }

    /** 出队 */
    public int pop() {
        checkOutStack();
        return outStack.pop();
    }

    /** 获取队头元素 */
    public int peek() {
        checkOutStack();
        return outStack.peek();
    }

    /** 是否为空 */
    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    private void checkOutStack() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
    }

}
