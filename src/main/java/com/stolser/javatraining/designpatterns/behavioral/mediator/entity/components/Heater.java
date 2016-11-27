package com.stolser.javatraining.designpatterns.behavioral.mediator.entity.components;

public class Heater extends AbstractComponent {
    public void on(int temp) {
        System.out.println("Heater.on()");
        if (mediator.checkTemperature(temp)) {
            System.out.println("Temperature is set to " + temp);
            mediator.heaterOff();
        }
    }

    public void off() {
        System.out.println("Heater.off()");
        mediator.wash();
    }
}
