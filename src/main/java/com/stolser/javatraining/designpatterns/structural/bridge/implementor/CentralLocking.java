package com.stolser.javatraining.designpatterns.structural.bridge.implementor;

public class CentralLocking implements Locking {
    private final String productName;

    public CentralLocking(String productName) {
        this.productName = productName;
    }

    @Override
    public String productName() {
        return productName;
    }

    @Override
    public void produce() {
        System.out.println("Producing Central Locking System");
    }
}