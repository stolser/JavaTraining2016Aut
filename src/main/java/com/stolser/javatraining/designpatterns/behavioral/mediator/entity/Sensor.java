package com.stolser.javatraining.designpatterns.behavioral.mediator.entity;

public class Sensor {
    public boolean checkTemperature(int temp) {
        System.out.println("Temperature reached " + temp + " *C");
        return true;
    }
}
