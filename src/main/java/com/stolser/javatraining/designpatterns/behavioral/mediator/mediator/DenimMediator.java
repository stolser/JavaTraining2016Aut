package com.stolser.javatraining.designpatterns.behavioral.mediator.mediator;

import com.stolser.javatraining.designpatterns.behavioral.mediator.entity.Sensor;
import com.stolser.javatraining.designpatterns.behavioral.mediator.entity.SoilRemoval;
import com.stolser.javatraining.designpatterns.behavioral.mediator.entity.components.Heater;
import com.stolser.javatraining.designpatterns.behavioral.mediator.entity.components.Machine;
import com.stolser.javatraining.designpatterns.behavioral.mediator.entity.components.Valve;

public class DenimMediator implements MachineMediator {
    private final Machine machine;
    private final Heater heater;
    private final Motor motor;
    private final Sensor sensor;
    private final SoilRemoval soilRemoval;
    private final Valve valve;

    public DenimMediator(Machine machine, Heater heater, Motor motor, Sensor sensor,
                         SoilRemoval soilRemoval, Valve valve) {
        this.machine = machine;
        this.heater = heater;
        this.motor = motor;
        this.sensor = sensor;
        this.soilRemoval = soilRemoval;
        this.valve = valve;
        System.out.println("------------------- Setting up for DENIM program");
    }

    @Override
    public void machineStart() {
        machine.start();
    }

    @Override
    public void wash() {
        motor.startMotor();
        motor.rotateDrum(1400);
        System.out.println("Adding detergent");
        soilRemoval.medium();
        System.out.println("Adding softener");
        System.out.println("Your laundry is done!");
    }

    @Override
    public void valveOpen() {
        valve.open();
    }

    @Override
    public void valveClose() {
        valve.closed();
    }

    @Override
    public void heaterOn() {
        heater.on(30);
    }

    @Override
    public void heaterOff() {
        heater.off();
    }

    @Override
    public boolean checkTemperature(int temp) {
        return sensor.checkTemperature(temp);
    }
}
