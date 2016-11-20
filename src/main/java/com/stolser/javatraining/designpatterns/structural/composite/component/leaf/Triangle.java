package com.stolser.javatraining.designpatterns.structural.composite.component.leaf;

import com.stolser.javatraining.designpatterns.structural.composite.component.Shape;

public class Triangle implements Shape {
    @Override
    public void draw() {
        System.out.println("I am a triangle.");
    }
}
