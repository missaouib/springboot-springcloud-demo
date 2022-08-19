package com.example.springbootspringclouddemo.inherit;

public class Son extends Father {

    public static void main(String[] args) {
        Son son = new Son();
        son.call("a");
        son.call("b");
        son.call("c");
        son.call("d");
        son.print();
    }

    public void print() {
        System.out.println(super.b);
        System.out.println(super.c);
        System.out.println(super.d);
    }
}
