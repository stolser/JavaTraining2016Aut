package com.stolser.javatraining.designpatterns.creational.factory.product;

class ProductA implements Product {
    private String name;

    public ProductA(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        System.out.println("getting the name of ProductA");
        return name;
    }

    @Override
    public void use() {
        System.out.println("using a ProductA");
    }

    @Override
    public int foo() {
        System.out.println("doing something with a ProductA");
        return 0;
    }
}
