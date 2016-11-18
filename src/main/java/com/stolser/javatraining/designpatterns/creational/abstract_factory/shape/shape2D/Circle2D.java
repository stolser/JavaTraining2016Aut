package com.stolser.javatraining.designpatterns.creational.abstract_factory.shape.shape2D;

public class Circle2D implements Shape2D {
    @Override
    public void move(double x, double y) {
        System.out.println("move a Circle2D");
    }

    @Override
    public void rotate(double angle) {
        System.out.println("rotate a Circle2D");
    }

    @Override
    public void draw() {
        System.out.println("draw a Circle2D");
    }
}
