package com.stolser.javatraining.designpatterns.creational.prototype;

import com.stolser.javatraining.designpatterns.creational.prototype.entity.Shape;

public class Client {
    public static void main(String[] args) throws CloneNotSupportedException {
        Shape circle1 = ShapeFactory.getInstance(ShapeFactory.ShapeType.CIRCLE);
        Shape circle2 = ShapeFactory.getInstance(ShapeFactory.ShapeType.CIRCLE);

        System.out.println("circle1 = " + circle1);
        System.out.println("circle2 = " + circle2);

        circle1.draw();
        circle2.draw();



    }
}
