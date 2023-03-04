package com.example.algorithm;

import java.util.Objects;

public class Singleton2 {

    private Singleton2() {}

    private static Singleton2 INSTANCE = null;

    public static synchronized Singleton2 getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new Singleton2();
        }
        return INSTANCE;
    }

}
