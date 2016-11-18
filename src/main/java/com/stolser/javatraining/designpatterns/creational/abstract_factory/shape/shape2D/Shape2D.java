package com.stolser.javatraining.designpatterns.creational.abstract_factory.shape.shape2D;

public interface Shape2D {
    void move(double x, double y);
    void rotate(double angle);
    void draw();
}
