package com.stolser.javatraining.designpatterns.behavioral.mediator;

import com.stolser.javatraining.designpatterns.behavioral.mediator.entity.Sensor;
import com.stolser.javatraining.designpatterns.behavioral.mediator.entity.SoilRemoval;
import com.stolser.javatraining.designpatterns.behavioral.mediator.entity.components.*;
import com.stolser.javatraining.designpatterns.behavioral.mediator.mediator.CottonMediator;
import com.stolser.javatraining.designpatterns.behavioral.mediator.mediator.DenimMediator;
import com.stolser.javatraining.designpatterns.behavioral.mediator.mediator.MachineMediator;
import com.stolser.javatraining.designpatterns.behavioral.mediator.mediator.Motor;

public class Client {
    public static void main(String[] args) {
        MachineMediator mediator;
        Sensor sensor = new Sensor();
        SoilRemoval soilRemoval = new SoilRemoval();
        Motor motor = new Motor();
        Machine machine = new Machine();
        Heater heater = new Heater();
        Valve valve = new Valve();
        Button button = new Button();

        mediator = new CottonMediator(machine, heater, motor, sensor, soilRemoval, valve);
        button.setMediator(mediator);
        machine.setMediator(mediator);
        heater.setMediator(mediator);
        valve.setMediator(mediator);
        button.press();

        mediator = new DenimMediator(machine, heater, motor, sensor, soilRemoval, valve);
        button.setMediator(mediator);
        machine.setMediator(mediator);
        heater.setMediator(mediator);
        valve.setMediator(mediator);
        button.press();
    }
}
