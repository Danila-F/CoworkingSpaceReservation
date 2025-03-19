package com.reservationapp;

import com.reservationapp.customclassloader.*;

import java.lang.reflect.InvocationTargetException;

public class ReservationApp {
    public static void main(String[] args) {
        try {
            String classPath = "classes";
            String className = "TestClass";

            MyClassLoader classLoader = new MyClassLoader(classPath);
            Class<?> myClass = classLoader.loadClass(className);

            Object object = myClass.getDeclaredConstructor().newInstance();
            myClass.getMethod("test").invoke(object);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException ex) {
            ex.printStackTrace();
        }
    }
}