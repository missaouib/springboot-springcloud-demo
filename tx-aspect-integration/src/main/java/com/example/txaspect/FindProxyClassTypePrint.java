package com.example.txaspect;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class FindProxyClassTypePrint {

    public static void main(String[] args) {
        File[] files = new File("com/sun/proxy").listFiles();
        Arrays.stream(files).filter(e->e.isFile()).
                forEach(e->{
                    try {
                        String proxyName = "com.sun.proxy."+e.getName().substring(0,e.getName().lastIndexOf("."));
                        byte[] proxyClassFile = IOUtils.toByteArray(new FileInputStream(e));
                        Constructor<Proxy> declaredConstructor = Proxy.class.getDeclaredConstructor();
                        declaredConstructor.setAccessible(true);
                        Proxy proxy = declaredConstructor.newInstance();
                        Method defineClass0 = Proxy.class.getDeclaredMethod("defineClass0", ClassLoader.class, String.class, byte[].class, int.class, int.class);
                        defineClass0.setAccessible(true);
                        Class cls = (Class)defineClass0.invoke(proxy, Thread.currentThread().getContextClassLoader(), proxyName, proxyClassFile, 0, proxyClassFile.length);
                        if (Arrays.stream(cls.getInterfaces()).filter(inf->inf.getName().contains("SynthesizedAnnotation")).findAny().isPresent()){
                            System.out.println(cls.getName());
                        }
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                });
    }


}
