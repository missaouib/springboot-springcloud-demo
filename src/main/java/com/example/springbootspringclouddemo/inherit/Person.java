package com.example.springbootspringclouddemo.inherit;

public interface Person {

    default void print() {
        System.out.println("default...");
    }
}
