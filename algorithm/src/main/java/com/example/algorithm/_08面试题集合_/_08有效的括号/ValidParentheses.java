package com.example.algorithm._08面试题集合_._08有效的括号;

import java.util.Stack;

/**
 * 有效的括号
 * https://leetcode.cn/problems/valid-parentheses/solution/
 */
public class ValidParentheses {

    public static void main(String[] args) {
        ValidParentheses vp = new ValidParentheses();
        System.out.println(vp.isValid("()[]"));
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0;i < s.length();i++) {
            char c = s.charAt(i);
            //左括号
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else { //右括号
                if (stack.isEmpty()) return false;
                Character leftChar = stack.pop();
                if (leftChar == '(' && c != ')') return false;
                if (leftChar == '{' && c != '}') return false;
                if (leftChar == '[' && c != ']') return false;
            }
        }
        return stack.isEmpty();
    }
}
