package com.stolser.javatraining.designpatterns.creational.abstract_factory.shape.shape3D;

public class Circle3D implements Shape3D {
    @Override
    public void move(double x, double y) {
        System.out.println("move a Circle3D");
    }

    @Override
    public void rotate(double angle) {
        System.out.println("rotate a Circle3D");
    }

    @Override
    public void draw() {
        System.out.println("draw a Circle3D");
    }
}
