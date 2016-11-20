package com.stolser.javatraining.designpatterns.structural.composite;

import com.stolser.javatraining.designpatterns.structural.composite.component.Shape;
import com.stolser.javatraining.designpatterns.structural.composite.component.leaf.Circle;
import com.stolser.javatraining.designpatterns.structural.composite.component.leaf.Rectangle;
import com.stolser.javatraining.designpatterns.structural.composite.component.leaf.Triangle;
import com.stolser.javatraining.designpatterns.structural.composite.composite.Composite;

public class Client {
    public static void main(String[] args) {
        Shape circle1 = new Circle();
        Shape circle2 = new Circle();
        Shape circle3 = new Circle();
        Shape circle4 = new Circle();
        Composite circles = new Composite();
        circles.addComponent(circle1);
        circles.addComponent(circle2);
        circles.addComponent(circle3);
        circles.addComponent(circle4);

        Shape rectangle1 = new Rectangle();
        Shape rectangle2 = new Rectangle();
        Shape rectangle3 = new Rectangle();
        Composite rectangles = new Composite();
        rectangles.addComponent(rectangle1);
        rectangles.addComponent(rectangle2);
        rectangles.addComponent(rectangle3);

        Shape triangle1 = new Triangle();
        Shape triangle2 = new Triangle();
        Shape triangle3 = new Triangle();
        Composite triangles = new Composite();
        triangles.addComponent(triangle1);
        triangles.addComponent(triangle2);
        triangles.addComponent(triangle3);

        Composite shapes = new Composite();
        shapes.addComponent(circles);
        shapes.addComponent(rectangles);
        shapes.addComponent(triangles);

        shapes.draw();
    }
}
