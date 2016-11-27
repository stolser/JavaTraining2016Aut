package com.stolser.javatraining.designpatterns.behavioral.delegate.delegatee;

public class Circle implements Graphics {
    @Override
    public void draw() {
        System.out.println("Drawing a circle.");
    }
}
