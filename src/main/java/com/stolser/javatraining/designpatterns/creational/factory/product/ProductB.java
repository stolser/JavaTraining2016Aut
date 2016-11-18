package com.stolser.javatraining.designpatterns.creational.factory.product;

class ProductB implements Product {
    private String name;

    public ProductB(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        System.out.println("getting the name of ProductB");
        return name;
    }

    @Override
    public void use() {
        System.out.println("using a ProductB");
    }

    @Override
    public int foo() {
        System.out.println("doing something with a ProductB");
        return 0;
    }
}
