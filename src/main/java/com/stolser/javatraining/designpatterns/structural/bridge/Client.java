package com.stolser.javatraining.designpatterns.structural.bridge;

import com.stolser.javatraining.designpatterns.structural.bridge.abstraction.BigWheel;
import com.stolser.javatraining.designpatterns.structural.bridge.abstraction.Car;
import com.stolser.javatraining.designpatterns.structural.bridge.abstraction.Motoren;
import com.stolser.javatraining.designpatterns.structural.bridge.implementor.CentralLocking;
import com.stolser.javatraining.designpatterns.structural.bridge.implementor.GearLocking;
import com.stolser.javatraining.designpatterns.structural.bridge.implementor.Locking;

public class Client {
    public static void main(String[] args) {
        Locking product = new CentralLocking("Central Locking System");
        Locking product2 = new GearLocking("Gear Locking System");

        Car car = new BigWheel(product, "BigWheel xz model");
        car.produceProduct();
        car.assemble();
        car.printDetails();
        System.out.println();

        car = new BigWheel(product2, "BigWheel xz model");
        car.produceProduct();
        car.assemble();
        car.printDetails();
        System.out.println();

        car = new Motoren(product, "Motoren lm model");
        car.produceProduct();
        car.assemble();
        car.printDetails();
        System.out.println();

        car = new Motoren(product2, "Motoren lm model");
        car.produceProduct();
        car.assemble();
        car.printDetails();
    }
}
