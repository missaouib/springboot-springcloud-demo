package com.example.algorithm;

public class Singleton4 {

    private Singleton4() {}

    private enum SingletonHolder {
        INSTANCE;

        private Singleton4 instance;

        private SingletonHolder() {
            instance = new Singleton4();
        }
    }

    public static Singleton4 getInstance() {
        return SingletonHolder.INSTANCE.instance;
    }

}
