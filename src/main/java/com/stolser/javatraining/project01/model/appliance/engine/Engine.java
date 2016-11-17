package com.stolser.javatraining.project01.model.appliance.engine;

public interface Engine {
    void start();
    void stop();
    boolean isRunning();
    double getInputPower();
}
