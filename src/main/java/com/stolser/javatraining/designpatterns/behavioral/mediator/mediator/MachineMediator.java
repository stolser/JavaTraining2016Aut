package com.stolser.javatraining.designpatterns.behavioral.mediator.mediator;

public interface MachineMediator {
    void machineStart();
    void wash();
    void valveOpen();
    void valveClose();
    void heaterOn();
    void heaterOff();
    boolean checkTemperature(int temp);
}
