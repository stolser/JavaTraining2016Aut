package com.stolser.javatraining.designpatterns.structural.delegate.delegatee;

public class Triangle implements Graphics {
    @Override
    public void draw() {
        System.out.println("Drawing a triangle.");
    }
}
