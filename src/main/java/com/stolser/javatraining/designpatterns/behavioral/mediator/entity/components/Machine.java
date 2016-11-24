package com.stolser.javatraining.designpatterns.behavioral.mediator.entity.components;

public class Machine extends AbstractComponent {
    public void start(){
        System.out.println("Machine.start()");
        mediator.valveOpen();
    }
    public void wash(){
        System.out.println("Machine.wash()");
        mediator.wash();
    }
}
