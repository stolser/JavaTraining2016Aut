package com.stolser.javatraining.designpatterns.creational.factory;

import com.stolser.javatraining.designpatterns.creational.factory.product.Product;
import com.stolser.javatraining.designpatterns.creational.factory.product.ProductFactory;

public class ClientRunner {
    public static void main(String[] args) {
        new Client().start();
    }
}

class Client {
    private ProductFactory factory;

    void start() {
        factory = ProductFactory.getInstance();

        Product product = factory.getProductA("simple product");
        doSomethingWithProduct(product);

        product = factory.getProductB("super product");
        doSomethingWithProduct(product);
    }

    private void doSomethingWithProduct(Product product) {
        System.out.println("product = " + product.getName());
        product.use();
        product.foo();
    }
}
