package com.stolser.javatraining.designpatterns.creational.prototype.entity;

public class Triangle extends Shape {
    public Triangle(String name) {
        super(name);
    }

    @Override
    public void draw() {
        System.out.printf("Drawing a triangle '%s'\n", getName());
    }
}
