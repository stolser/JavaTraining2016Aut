package com.stolser.javatraining.designpatterns.creational.abstract_factory.factory;

public class ShapeProvider {
    public static ShapeFactory getCircleFactory() {
        return new CircleFactory();
    }

    public static ShapeFactory getRectangleFactory() {
        return new RectangleFactory();
    }

    public static ShapeFactory getTriangleFactory() {
        return new TriangleFactory();
    }
}
