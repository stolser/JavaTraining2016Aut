package com.stolser.javatraining.designpatterns.creational.factory.product;

public class ProductFactory {
    private ProductFactory() {
    }

    public Product getProductA(String name) {
        return new ProductA(name);
    }

    public Product getProductB(String name) {
        return new ProductB(name);
    }

    public static ProductFactory getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private static final class InstanceHolder {
        private static final ProductFactory INSTANCE = new ProductFactory();
    }

}
