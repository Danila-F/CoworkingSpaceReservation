package com.reservationapp.customclassloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MyClassLoader extends ClassLoader {
    private final String classPath;

    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Class<?> loadedClass = findLoadedClass(name);
        if (loadedClass == null) {
            try {
                loadedClass = super.loadClass(name, false);
            } catch (ClassNotFoundException ex) {
                loadedClass = findClass(name);
            }
        }
        return loadedClass;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String filePath = classPath + File.separator + name + ".class";
        try(FileInputStream fileInputStream = new FileInputStream(filePath)) {
            byte[] classBytes = fileInputStream.readAllBytes();
            return defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException ex) {
            throw new ClassNotFoundException(name, ex);
        }
    }
}
