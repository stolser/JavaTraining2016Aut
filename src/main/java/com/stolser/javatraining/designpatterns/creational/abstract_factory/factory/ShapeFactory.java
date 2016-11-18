package com.stolser.javatraining.designpatterns.creational.abstract_factory.factory;

import com.stolser.javatraining.designpatterns.creational.abstract_factory.shape.shape2D.Shape2D;
import com.stolser.javatraining.designpatterns.creational.abstract_factory.shape.shape3D.Shape3D;

public interface ShapeFactory {
    Shape2D get2D();
    Shape3D get3D();
}
