package com.stolser.javatraining.designpatterns.structural.composite.component.leaf;

import com.stolser.javatraining.designpatterns.structural.composite.component.Shape;

public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("I am a rectangle.");
    }
}
