package com.example.algorithm;

import java.util.Objects;

public class Singleton3 {

    private Singleton3() {}

    private static volatile Singleton3 INSTANCE = null;

    public static Singleton3 getInstance() {
        if (Objects.isNull(INSTANCE)) {
            synchronized (Singleton3.class) {
                if (Objects.isNull(INSTANCE)) {
                    INSTANCE = new Singleton3();
                }
            }
        }
        return INSTANCE;
    }
}
