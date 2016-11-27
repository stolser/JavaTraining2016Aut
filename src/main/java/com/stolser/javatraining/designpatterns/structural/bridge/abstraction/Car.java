package com.stolser.javatraining.designpatterns.structural.bridge.abstraction;

import com.stolser.javatraining.designpatterns.structural.bridge.implementor.Locking;

public abstract class Car {
    private final Locking product;
    private final String carType;

    public Car(Locking product, String carType) {
        this.product = product;
        this.carType = carType;
    }

    public abstract void assemble();

    public abstract void produceProduct();

    public void printDetails() {
        System.out.println("Car: " + carType + ", Product:" + product.productName());
    }
}
