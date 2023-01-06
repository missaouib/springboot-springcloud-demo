package com.example.javaconcurrent.classloader.simple;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class SimpleClassLoader extends ClassLoader {

    private static final String DEFAULT_DIR = "E:\\developer\\IdeaProjects\\springboot-springcloud-demo\\java-concurrent";

    private String dir = DEFAULT_DIR;

    private String classLoaderName;

    public SimpleClassLoader() {}

    public SimpleClassLoader(String classLoaderName) {
        super();
        this.classLoaderName = classLoaderName;
    }


    public SimpleClassLoader(String classLoaderName,ClassLoader parent) {
        super(parent);
        this.classLoaderName = classLoaderName;
    }

    @Override
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        return super.loadClass(name, resolve);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String classPath = name.replace(".","/");
        File classFile = new File(dir,classPath+".class");
        if (!classFile.exists()) {
            throw new ClassNotFoundException("The class "+name + " not found under "+dir);
        }
        byte[] classBytes = loadClassBytes(classFile);
        if (classBytes == null || classBytes.length == 0) {
            throw new ClassNotFoundException("load the class "+name + " failed");
        }
        return this.defineClass(name,classBytes,0,classBytes.length);
    }

    private byte[] loadClassBytes(File classFile) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             FileInputStream fis = new FileInputStream(classFile)) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer,0,len);
            }
            baos.flush();
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
}
