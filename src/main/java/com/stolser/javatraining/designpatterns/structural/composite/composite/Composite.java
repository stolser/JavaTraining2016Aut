package com.stolser.javatraining.designpatterns.structural.composite.composite;

import com.stolser.javatraining.designpatterns.structural.composite.component.Shape;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Shape {
    private List<Shape> components = new ArrayList<>();

    public void addComponent(Shape newComponent) {
        components.add(newComponent);
    }

    public void removeComponent(Shape component) {
        components.remove(component);
    }

    @Override
    public void draw() {
        System.out.println("Composite {");
        components.forEach(Shape::draw);
        System.out.println("}");
    }
}
