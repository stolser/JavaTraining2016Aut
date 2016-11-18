package com.stolser.javatraining.designpatterns.creational.abstract_factory.factory;

import com.stolser.javatraining.designpatterns.creational.abstract_factory.shape.shape2D.Circle2D;
import com.stolser.javatraining.designpatterns.creational.abstract_factory.shape.shape2D.Shape2D;
import com.stolser.javatraining.designpatterns.creational.abstract_factory.shape.shape3D.Circle3D;
import com.stolser.javatraining.designpatterns.creational.abstract_factory.shape.shape3D.Shape3D;

class CircleFactory implements ShapeFactory {
    @Override
    public Shape2D get2D() {
        return new Circle2D();
    }

    @Override
    public Shape3D get3D() {
        return new Circle3D();
    }
}
