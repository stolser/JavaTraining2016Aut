package com.stolser.javatraining.designpatterns.creational.prototype.entity;

public abstract class Shape implements Cloneable {
    private String name;

    public Shape(String name) {
        this.name = name;
    }

    public abstract void draw();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
