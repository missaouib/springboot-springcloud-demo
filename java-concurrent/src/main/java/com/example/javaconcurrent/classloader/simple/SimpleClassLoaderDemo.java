package com.example.javaconcurrent.classloader.simple;

public class SimpleClassLoaderDemo {

    public static void main(String[] args) throws Exception {
        SimpleClassLoader classLoader = new SimpleClassLoader("simple");
        Class<?> aClass = classLoader.loadClass("com.example.SimpleObject",true);
        System.out.println(aClass);
        System.out.println(aClass.getClassLoader());

        Object o = aClass.newInstance();
        System.out.println(o);

    }

}
