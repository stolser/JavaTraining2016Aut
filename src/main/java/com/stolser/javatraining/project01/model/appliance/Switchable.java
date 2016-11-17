package com.stolser.javatraining.project01.model.appliance;

public interface Switchable {
    void switchOn();
    void switchOff();
    boolean isOn();
    double getMaxPower();
    double getCurrentPower();
}
