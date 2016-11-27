package com.stolser.javatraining.designpatterns.structural.bridge.abstraction;

import com.stolser.javatraining.designpatterns.structural.bridge.implementor.Locking;

public class Motoren extends Car {
    private final Locking product;
    private final String carType;

    public Motoren(Locking product, String carType) {
        super(product, carType);
        this.product = product;
        this.carType = carType;
    }

    @Override
    public void assemble() {
        System.out.printf("Assembling %s for %s%n", product.productName(), carType);
    }

    @Override
    public void produceProduct() {
        product.produce();
        System.out.printf("Modifying product %s according to %s%n", product.productName(), carType);
    }
}