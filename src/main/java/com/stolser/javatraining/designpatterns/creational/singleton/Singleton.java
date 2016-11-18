package com.stolser.javatraining.designpatterns.creational.singleton;

public class Singleton {
    private Singleton() {}

    private static final class InstanceHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void foo() {
        System.out.println("doing something");
    }
}
