package com.stolser.javatraining.designpatterns.behavioral.mediator.entity.components;

public class Valve extends AbstractComponent {
    public void open() {
        System.out.println("Valve.open()");
        System.out.println("Filling water...");
        mediator.valveClose();
    }

    public void closed() {
        System.out.println("Valve.closed()");
        mediator.heaterOn();
    }
}
