package com.stolser.javatraining.designpatterns.creational.abstract_factory.factory;

import com.stolser.javatraining.designpatterns.creational.abstract_factory.shape.shape2D.Shape2D;
import com.stolser.javatraining.designpatterns.creational.abstract_factory.shape.shape2D.Triangle2D;
import com.stolser.javatraining.designpatterns.creational.abstract_factory.shape.shape3D.Shape3D;
import com.stolser.javatraining.designpatterns.creational.abstract_factory.shape.shape3D.Triangle3D;

class TriangleFactory implements ShapeFactory {
    @Override
    public Shape2D get2D() {
        return new Triangle2D();
    }

    @Override
    public Shape3D get3D() {
        return new Triangle3D();
    }
}
