package com.example.javaconcurrent.singleton;

/**
 * 枚举实现单例
 */
public class SingletonObject7 {

    private SingletonObject7(){

    }

    /**
     * 枚举类型是线程安全的，并且只会装载一次
     * 问题1：枚举单例在创建时是否有并发问题？ 没有
     * 问题2：枚举单例能否被反射破坏单例？不会
     * 问题3：枚举单例能否被反序列化破坏单例？不会
     * 问题4：枚举单例属于懒汉式还是饿汉式？饿汉式
     * 问题5：枚举单例如果希望加入一些单例创建时的初始化逻辑该如何做？在构造方法中处理
     */
    private enum Singleton{
        INSTANCE;

        private final SingletonObject7 instance;

        Singleton(){
            instance = new SingletonObject7();
        }

        private SingletonObject7 getInstance(){
            return instance;
        }
    }

    public static SingletonObject7 getInstance(){

        return Singleton.INSTANCE.getInstance();
    }
}