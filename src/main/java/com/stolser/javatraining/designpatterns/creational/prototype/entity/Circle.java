package com.stolser.javatraining.designpatterns.creational.prototype.entity;

public class Circle extends Shape {
    public Circle(String name) {
        super(name);
    }

    @Override
    public void draw() {
        System.out.printf("Drawing a circle '%s'.\n", getName());
    }
}
