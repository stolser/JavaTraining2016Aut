package com.stolser.javatraining.project01.model.appliance.tools;

public interface Accumulator {
    void charge();
    void switchOn();
    void switchOff();
    boolean isOn();
    void usePower();
    int getCurrentCharge();
}
