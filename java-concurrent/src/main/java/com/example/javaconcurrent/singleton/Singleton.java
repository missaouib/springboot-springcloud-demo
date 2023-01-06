package com.example.javaconcurrent.singleton;

import sun.misc.Unsafe;

import java.io.Serializable;

/**
 * 饿汉式实现单例
 */
//问题1：为什么加final? 避免子类覆盖父类的方法导致破坏了单例
//问题2：如果实现了序列化接口，还要做什么来防止反序列化破坏单例? 重写readResolve方法
public final class Singleton implements Serializable {
    //问题3：为什么设置为私有？禁止外部类创建对象   是否能防止反射创建新的实例？ 不能
    private Singleton(){}
    //问题4：这样初始化是否能保证单例对象创建时的线程安全？可以，由jvm类加载器保证线程安全
    private static final Singleton INSTANCE = new Singleton();
    //问题5：为什么提供静态方法而不是直接将INSTANCE设置为public，说出你知道的理由
    public static Singleton getInstance() {
        return INSTANCE;
    }


    public Object readResolve() {
        return INSTANCE;
    }
}
