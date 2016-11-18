package com.stolser.javatraining.designpatterns.creational.abstract_factory.shape.shape3D;

public class Triangle3D implements Shape3D {
    @Override
    public void move(double x, double y) {
        System.out.println("move a Triangle3D");
    }

    @Override
    public void rotate(double angle) {
        System.out.println("rotate a Triangle3D");
    }

    @Override
    public void draw() {
        System.out.println("draw a Triangle3D");
    }
}
