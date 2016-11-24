package com.stolser.javatraining.designpatterns.behavioral.mediator.mediator;

public class Motor {
    public void startMotor() {
        System.out.println("Motor.startMotor()");
    }

    public void rotateDrum(int velocity) {
        System.out.println("Motor: rotating drum at " + velocity + " rpm");
    }
}
