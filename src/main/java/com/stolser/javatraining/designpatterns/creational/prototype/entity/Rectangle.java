package com.stolser.javatraining.designpatterns.creational.prototype.entity;

public class Rectangle extends Shape {
    public Rectangle(String name) {
        super(name);
    }

    @Override
    public void draw() {
        System.out.printf("Drawing a rectangle '%s'.\n", getName());
    }
}
