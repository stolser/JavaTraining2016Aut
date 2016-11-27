package com.stolser.javatraining.designpatterns.behavioral.mediator.entity.components;

public class Button extends AbstractComponent {
    public void press(){
        System.out.println("Button.press()");
        mediator.machineStart();
    }
}
