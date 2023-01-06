package com.example.javaconcurrent.classloader.brokeparent;

/**
 * 重写loadClass方法，就算打破了双亲委派机制也无法加载自定义的java.lang包
 */
public class SimpleClassLoaderDemo {

    public static void main(String[] args) throws Exception {
        SimpleClassLoader classLoader = new SimpleClassLoader("simple");
        Class<?> aClass = classLoader.loadClass("com.example.SimpleObject");
        System.out.println(aClass);
        System.out.println(aClass.getClassLoader());

        Object o = aClass.newInstance();
        System.out.println(o);


        //对于jvm自带的类：Prohibited package name: java.lang
        Class<?> bClass = classLoader.loadClass("java.lang.Boolean");
        System.out.println(bClass);
    }

}
