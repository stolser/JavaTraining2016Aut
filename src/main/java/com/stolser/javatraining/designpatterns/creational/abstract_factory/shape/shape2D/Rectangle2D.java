package com.stolser.javatraining.designpatterns.creational.abstract_factory.shape.shape2D;

public class Rectangle2D implements Shape2D {
    @Override
    public void move(double x, double y) {
        System.out.println("move a Rectangle2D");
    }

    @Override
    public void rotate(double angle) {
        System.out.println("rotate a Rectangle2D");
    }

    @Override
    public void draw() {
        System.out.println("draw a Rectangle2D");
    }
}
