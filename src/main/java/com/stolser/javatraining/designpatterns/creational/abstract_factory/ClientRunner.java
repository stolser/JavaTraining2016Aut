package com.stolser.javatraining.designpatterns.creational.abstract_factory;

import com.stolser.javatraining.designpatterns.creational.abstract_factory.factory.ShapeFactory;
import com.stolser.javatraining.designpatterns.creational.abstract_factory.factory.ShapeProvider;
import com.stolser.javatraining.designpatterns.creational.abstract_factory.shape.shape2D.Shape2D;
import com.stolser.javatraining.designpatterns.creational.abstract_factory.shape.shape3D.Shape3D;

public class ClientRunner {
    public static void main(String[] args) {
        new Client().start();
    }
}

class Client {
    private Shape2D shape2D;
    private Shape3D shape3D;

    void start() {
        getShapesFromFactory(ShapeProvider.getCircleFactory());
        doSomethingWithShapes();

        getShapesFromFactory(ShapeProvider.getRectangleFactory());
        doSomethingWithShapes();

        getShapesFromFactory(ShapeProvider.getTriangleFactory());
        doSomethingWithShapes();
    }

    private void getShapesFromFactory(ShapeFactory factory) {
        shape2D = factory.get2D();
        shape3D = factory.get3D();
    }

    private void doSomethingWithShapes() {
        shape2D.move(1, 3);
        shape2D.rotate(34);
        shape2D.draw();
        System.out.println("-------------------");

        shape3D.move(1, 3);
        shape3D.rotate(34);
        shape3D.draw();
        System.out.println("==============================================");
    }
}
